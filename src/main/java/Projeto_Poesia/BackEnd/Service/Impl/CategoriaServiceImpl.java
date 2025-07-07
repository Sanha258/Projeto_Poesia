package Projeto_Poesia.BackEnd.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Projeto_Poesia.BackEnd.DTO.CategoriaDTO;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Repository.CategoriaRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Repository.PoemaRepository;
import Projeto_Poesia.BackEnd.Service.CategoriaService;
import Projeto_Poesia.BackEnd.Mapper.CategoriaMapper;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PoemaRepository poemaRepository;
    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public CategoriaEntity cadastrarCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaRepository.findByNomeIgnoreCase(categoriaDTO.getNome()).isPresent()) {
            throw new IllegalArgumentException("Está categoria já existe!");
        }

        UsuarioEntity usuario = usuarioRepository.findById(categoriaDTO.getUsuarioId())
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        CategoriaEntity categoria = categoriaMapper.toEntity(categoriaDTO, usuario);
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<CategoriaEntity> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaEntity buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));
    }

    @Override
    public void deletarCategoria(Long id, Long usuarioId) {
        CategoriaEntity categoria = buscarCategoriaPorId(id);

        if (!categoria.getUsuario().getId().equals(usuarioId)) {
            throw new IllegalArgumentException("Você não tem permissão para excluir esta categoria!");
        }

        // Verificar se existem poemas associados a esta categoria
        List<PoemaEntity> poemasAssociados = poemaRepository.findByCategoriaId(id);
        if (!poemasAssociados.isEmpty()) {
            throw new IllegalArgumentException("Não é possível excluir esta categoria pois existem poemas associados a ela. Delete os poemas primeiro.");
        }

        categoriaRepository.delete(categoria);
    }
    
}
