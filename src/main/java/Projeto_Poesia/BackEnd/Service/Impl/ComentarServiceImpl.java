package Projeto_Poesia.BackEnd.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ComentarServiceImpl implements ComentarService{
    
    @Autowired
    private ComentarRepository comentarRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PoemaRepository poemaRepository;

    @Override
    @Transactional
    public ComentarEntity criarComentario(ComentarDTO comentarDTO) {
        validarComentario(comentarDTO);
        
        UsuarioEntity autor = usuarioRepository.findById(comentarDTO.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        PoemaEntity poema = poemaRepository.findById(comentarDTO.getPoemaId())
                .orElseThrow(() -> new IllegalArgumentException("Poema não encontrado"));

        ComentarEntity comentario = new ComentarEntity();
        comentario.setTexto(comentarDTO.getTexto().trim());
        comentario.setAutor(autor);
        comentario.setPoema(poema);

        return comentarRepository.save(comentario);
    }

    @Override
    @Transactional
    public ComentarEntity editarComentario(Long id, ComentarDTO comentarDTO) {
        validarComentario(comentarDTO);
        
        ComentarEntity comentario = comentarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado"));
        
        if (!comentario.getAutor().getId().equals(comentarDTO.getAutorId())) {
            throw new IllegalArgumentException("Apenas o autor pode editar o comentário");
        }

        comentario.setTexto(comentarDTO.getTexto().trim());
        comentario.setEditado(true);
        comentario.setDataEdicao(LocalDateTime.now());
        
        return comentarRepository.save(comentario);
    }

    @Override
    @Transactional
    public void deletarComentario(Long id, Long autorId) {
        ComentarEntity comentario = comentarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado"));
        
        if (!comentario.getAutor().getId().equals(autorId)) {
            throw new IllegalArgumentException("Apenas o autor pode excluir o comentário");
        }
        
        comentarRepository.delete(comentario);
    }

    @Override
    public List<ComentarEntity> listarPorPoema(Long poemaId) {
        if (!poemaRepository.existsById(poemaId)) {
            throw new IllegalArgumentException("Poema não encontrado");
        }
        return comentarRepository.findByPoemaId(poemaId);
    }

    @Override
    public List<ComentarEntity> listarPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return comentarRepository.findByAutorId(usuarioId);
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
