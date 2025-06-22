package Projeto_Poesia.BackEnd.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.PoemaDTO;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Repository.CategoriaRepository;
import Projeto_Poesia.BackEnd.Repository.PoemaRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Service.PoemaService;
import Projeto_Poesia.BackEnd.Service.util.ValidacaoUtil;
import jakarta.transaction.Transactional;

@Service
public class PoemaServiceImp implements PoemaService {

    @Autowired
    private PoemaRepository poemaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public PoemaEntity cadastrarPoema(PoemaDTO poemaDTO) {
        try {
            // Validação dos campos obrigatórios
            if (poemaDTO.getTitulo() == null || poemaDTO.getTitulo().trim().isEmpty()) {
                throw new IllegalArgumentException("O título do poema é obrigatório.");
            }
           
            if (poemaDTO.getConteudo() == null || poemaDTO.getConteudo().trim().isEmpty()) {
                throw new IllegalArgumentException("O conteúdo do poema é obrigatório.");
            }

             // Validação de caracteres especiais
            ValidacaoUtil.validarCaracteres(poemaDTO.getTitulo(), "título");
            ValidacaoUtil.validarCaracteres(poemaDTO.getConteudo(), "conteúdo");

            if (poemaDTO.getConteudo().length() > 10000) {
                throw new IllegalArgumentException("O conteúdo do poema deve ter no máximo 500 caracteres.");
            }

            // Validação de caracteres especiais
            ValidacaoUtil.validarCaracteres(poemaDTO.getTitulo(), "título");
            ValidacaoUtil.validarCaracteres(poemaDTO.getConteudo(), "conteúdo");

            // Busca autor e categoria com tratamento de erro mais descritivo
            UsuarioEntity autor = usuarioRepository.findById(poemaDTO.getAutor())
                .orElseThrow(() -> new IllegalArgumentException("Autor com ID " + poemaDTO.getAutor() + " não encontrado."));

            CategoriaEntity categoria = categoriaRepository.findById(poemaDTO.getCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoria com ID " + poemaDTO.getCategoria() + " não encontrada."));

             PoemaEntity poema = new PoemaEntity();
             poema.setTitulo(poemaDTO.getTitulo().trim());
             poema.setConteudo(poemaDTO.getConteudo().trim());
             poema.setData(LocalDateTime.now()); // ADIÇÃO CRUCIAL
             poema.setAutor(autor);
             poema.setCategoria(categoria);

             return poemaRepository.save(poema);
            
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Erro de integridade de dados: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public List<PoemaEntity> listarPoemas() {
        return poemaRepository.findAll();
    }

    @Override
    public PoemaEntity buscarPoema(Long id) {
        return poemaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Poema com ID " + id + " não encontrado."));
    }

    @Override
    @Transactional
    public void deletarPoema(Long id, Long autorId) {
        try {
            PoemaEntity poema = poemaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Poema com ID " + id + " não encontrado."));

            if (!poema.getAutor().getId().equals(autorId)) {
                throw new IllegalArgumentException("Apenas o autor pode excluir o poema. ID do autor fornecido: " + autorId);
            }

            poemaRepository.delete(poema);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Erro ao excluir poema: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    @Transactional
    public PoemaEntity atualizarPoema(Long id, PoemaDTO poemaDTO) {
        try {
            PoemaEntity poemaExistente = poemaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Poema não encontrado."));

            // Verifica se o autor do DTO é o mesmo do poema existente
            if (!poemaExistente.getAutor().getId().equals(poemaDTO.getAutor())) {
                throw new IllegalArgumentException("Apenas o autor pode editar o poema.");
            }

            // Validações
            if (poemaDTO.getTitulo() != null && !poemaDTO.getTitulo().trim().isEmpty()) {
                ValidacaoUtil.validarCaracteres(poemaDTO.getTitulo(), "título");
                poemaExistente.setTitulo(poemaDTO.getTitulo().trim());
            }

            if (poemaDTO.getConteudo() != null && !poemaDTO.getConteudo().trim().isEmpty()) {
                ValidacaoUtil.validarCaracteres(poemaDTO.getConteudo(), "conteúdo");
                if (poemaDTO.getConteudo().length() > 10000) {
                    throw new IllegalArgumentException("O conteúdo deve ter no máximo 10.000 caracteres.");
                }
                poemaExistente.setConteudo(poemaDTO.getConteudo().trim());
            }

            if (poemaDTO.getCategoria() != null) {
                CategoriaEntity categoria = categoriaRepository.findById(poemaDTO.getCategoria())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
                poemaExistente.setCategoria(categoria);
            }

            return poemaRepository.save(poemaExistente);
            
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Erro de integridade de dados: " + e.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public List<PoemaEntity> listarPorAutor(Long autorId) {
        if (autorId == null || autorId <= 0) {
            throw new IllegalArgumentException("ID do autor inválido");
        }
        
        // Verifica se o autor existe antes de buscar os poemas
        if (!usuarioRepository.existsById(autorId)) {
            throw new IllegalArgumentException("Autor com ID " + autorId + " não encontrado");
        }
        
        return poemaRepository.findByAutorId(autorId);
    }

    @Override
    public List<PoemaEntity> buscarPorTitulo(String titulo) {
        return poemaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<PoemaEntity> listarPorCategoria(Long categoriaId) {
        if (categoriaId == null || categoriaId <= 0) {
            throw new IllegalArgumentException("ID da categoria inválido");
        }
    
        return poemaRepository.findByCategoriaId(categoriaId);
    }

}
    

