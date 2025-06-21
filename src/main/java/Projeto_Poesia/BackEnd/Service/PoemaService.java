package Projeto_Poesia.BackEnd.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.PoemaDTO;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;

@Service
public interface PoemaService {
    
    PoemaEntity cadastrarPoema(PoemaDTO poemaDTO);
    List<PoemaEntity> listarPoemas();
    PoemaEntity buscarPoema(Long id);
    PoemaEntity atualizarPoema(Long id, PoemaDTO poemaDTO);
    void deletarPoema(Long id, Long autor);
    List<PoemaEntity> listarPorAutor(Long autor);
    List<PoemaEntity> listarPorCategoria(Long categoria);
    List<PoemaEntity> buscarPorTitulo(String titulo);


    
}
