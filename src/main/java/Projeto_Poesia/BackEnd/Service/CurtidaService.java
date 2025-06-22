package Projeto_Poesia.BackEnd.Service;

import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.CurtidaDTO;

@Service
public interface CurtidaService {

    void CurtirPoema(CurtidaDTO curtidaDTO);
    void descurtirPoema(CurtidaDTO curtidaDTO);
    boolean verificarCurtida(CurtidaDTO curtidaDTO);
    int contarCurtidasPoema(Long poemaId);
    
}
