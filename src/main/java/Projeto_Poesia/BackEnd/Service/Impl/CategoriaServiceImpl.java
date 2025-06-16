package Projeto_Poesia.BackEnd.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.CategoriaDTO;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Repository.CategoriaRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CategoriaEntity cadastrarCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaRepository.findByNomeIgnoreCase(categoriaDTO.getNome()).isPresent()) {
            throw new IllegalArgumentException("Está categoria já existe!");
        }

        UsuarioEntity usuario = usuarioRepository.findById(categoriaDTO.getUsuarioId()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        CategoriaEntity categoria = new CategoriaEntity(categoriaDTO.getNome(), usuario);
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<CategoriaEntity> listarCategorias() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCategorias'");
    }

    @Override
    public Optional<CategoriaEntity> buscarCategoriaPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarCategoriaPorId'");
    }

    @Override
    public void deletarCategoria(Long id, Long usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarCategoria'");
    }
    


}
