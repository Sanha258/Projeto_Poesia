package Projeto_Poesia.BackEnd.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Projeto_Poesia.BackEnd.DTO.UsuarioDTO;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

@Service
public interface UsuarioService {

   UsuarioEntity cadastrarUsuario(UsuarioDTO usuarioDTO);

   List<UsuarioEntity> listarUsuarios();

   Page<UsuarioDTO> listar(Pageable pageable);

   Optional<UsuarioEntity> buscarUsuarioPorId(Long id);

   UsuarioEntity atualizarUsuario(Long id, UsuarioDTO usuarioDTO);

   void deletarUsuario(Long id);
    
}
