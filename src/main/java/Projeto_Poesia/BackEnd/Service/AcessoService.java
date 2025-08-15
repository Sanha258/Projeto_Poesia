package Projeto_Poesia.BackEnd.Service;

import org.springframework.stereotype.Service;
import Projeto_Poesia.BackEnd.DTO.AcessoDTO;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

@Service
public interface AcessoService {
    
    UsuarioEntity autenticar(AcessoDTO acessoDTO);
} 