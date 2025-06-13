package Projeto_Poesia.BackEnd.Service;

import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.UsuarioDTO;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

@Service
public interface UsuarioService {

   UsuarioEntity cadastrarUsuario(UsuarioDTO usuarioDTO);

   void deletarUsuario(Long id);
    
}
