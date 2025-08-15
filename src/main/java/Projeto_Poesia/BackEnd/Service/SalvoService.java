package Projeto_Poesia.BackEnd.Service;

import Projeto_Poesia.BackEnd.Entity.SalvoEntity;
import java.util.List;

public interface SalvoService {

    SalvoEntity salvar(Long usuarioId, Long poemaId);

    void remover(Long usuarioId, Long poemaId);

    List<SalvoEntity> listarPorUsuario(Long usuarioId);
    
    boolean poemaSalvo(Long usuarioId, Long poemaId);
} 