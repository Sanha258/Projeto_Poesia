package Projeto_Poesia.BackEnd.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.ComentarDTO;
import Projeto_Poesia.BackEnd.Entity.ComentarEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Repository.ComentarRepository;
import Projeto_Poesia.BackEnd.Repository.PoemaRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Service.ComentarService;
import jakarta.transaction.Transactional;

@Service
public class ComentarServiceImpl implements ComentarService {

    @Autowired
    private ComentarRepository comentarRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PoemaRepository poemaRepository;

    @Override
    @Transactional
    public ComentarEntity criarComentario(ComentarDTO comentarDTO) {
        try {
            // Validação básica do DTO
            if (comentarDTO == null) {
                throw new IllegalArgumentException("DTO de comentário não pode ser nulo");
            }
            
            validarComentario(comentarDTO);

            // Verifica se os IDs foram fornecidos
            if (comentarDTO.getAutorId() == null || comentarDTO.getPoemaId() == null) {
                throw new IllegalArgumentException("IDs de autor e poema são obrigatórios");
            }

            // Busca as entidades relacionadas
            UsuarioEntity autor = usuarioRepository.findById(comentarDTO.getAutorId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + comentarDTO.getAutorId()));
            
            PoemaEntity poema = poemaRepository.findById(comentarDTO.getPoemaId())
                    .orElseThrow(() -> new IllegalArgumentException("Poema não encontrado com ID: " + comentarDTO.getPoemaId()));

            // Cria e salva o comentário
            ComentarEntity comentario = new ComentarEntity();
            comentario.setTexto(comentarDTO.getTexto().trim());
            comentario.setAutor(autor);
            comentario.setPoema(poema);

            return comentarRepository.save(comentario);
            
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Erro de integridade ao salvar comentário: " + e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new IllegalStateException("Erro inesperado ao criar comentário: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ComentarEntity editarComentario(Long id, ComentarDTO comentarDTO) {
        try {
            validarComentario(comentarDTO);
            
            ComentarEntity comentario = comentarRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado com ID: " + id));
            
            // Verifica se o autor do comentário é o mesmo que está tentando editar
            if (!comentario.getAutor().getId().equals(comentarDTO.getAutorId())) {
                throw new IllegalArgumentException("Apenas o autor pode editar o comentário");
            }

            comentario.setTexto(comentarDTO.getTexto().trim());
            comentario.setEditado(true);
            comentario.setDataEdicao(LocalDateTime.now());
            
            return comentarRepository.save(comentario);
            
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao editar comentário: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deletarComentario(Long id, Long autorId) {
        try {
            ComentarEntity comentario = comentarRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado com ID: " + id));
            
            if (!comentario.getAutor().getId().equals(autorId)) {
                throw new IllegalArgumentException("Apenas o autor pode excluir o comentário");
            }
            
            comentarRepository.delete(comentario);
            
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao deletar comentário: " + e.getMessage());
        }
    }

    @Override
    public List<ComentarEntity> listarPorPoema(Long poemaId) {
        try {
            if (!poemaRepository.existsById(poemaId)) {
                throw new IllegalArgumentException("Poema não encontrado com ID: " + poemaId);
            }
            return comentarRepository.findByPoemaId(poemaId);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao listar comentários por poema: " + e.getMessage());
        }
    }

    @Override
    public List<ComentarEntity> listarPorUsuario(Long usuarioId) {
        try {
            if (!usuarioRepository.existsById(usuarioId)) {
                throw new IllegalArgumentException("Usuário não encontrado com ID: " + usuarioId);
            }
            return comentarRepository.findByAutorId(usuarioId);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao listar comentários por usuário: " + e.getMessage());
        }
    }

    @Override
    public ComentarEntity buscarComentarioPorId(Long id) {
        try {
            return comentarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado com ID: " + id));
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao buscar comentário: " + e.getMessage());
        }
    }

    private void validarComentario(ComentarDTO comentarDTO) {
        if (comentarDTO.getTexto() == null || comentarDTO.getTexto().trim().isEmpty()) {
            throw new IllegalArgumentException("Texto do comentário não pode ser vazio");
        }
        
        if (comentarDTO.getTexto().length() > 2000) {
            throw new IllegalArgumentException("Comentário não pode exceder 2000 caracteres");
        }
    }
}
    

