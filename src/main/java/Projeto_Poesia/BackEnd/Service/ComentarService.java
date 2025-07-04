package Projeto_Poesia.BackEnd.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.ComentarDTO;
import Projeto_Poesia.BackEnd.Entity.ComentarEntity;

@Service
public interface ComentarService {

    ComentarEntity criarComentario(ComentarDTO comentarDTO);
    ComentarEntity editarComentario(Long id, ComentarDTO comentarDTO);
    void deletarComentario(Long id, Long autorId);
    List<ComentarEntity> listarPorPoema(Long poemaId);
    List<ComentarEntity> listarPorUsuario(Long usuarioId);
    ComentarEntity buscarComentarioPorId(Long id);
    
}
