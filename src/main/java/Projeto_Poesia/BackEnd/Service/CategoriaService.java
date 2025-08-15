package Projeto_Poesia.BackEnd.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import Projeto_Poesia.BackEnd.DTO.CategoriaDTO;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;

@Service
public interface CategoriaService {

    CategoriaEntity cadastrarCategoria(CategoriaDTO categoriaDTO);

    List<CategoriaEntity> listarCategorias();

    CategoriaEntity buscarCategoriaPorId(Long id);

    void deletarCategoria(Long id, Long usuarioId);
    
} 