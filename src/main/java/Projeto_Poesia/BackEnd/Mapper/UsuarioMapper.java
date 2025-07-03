package Projeto_Poesia.BackEnd.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Projeto_Poesia.BackEnd.DTO.UsuarioDTO;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Mapper.AcessoMapper;

@Component
public class UsuarioMapper {
    @Autowired 
    private AcessoMapper acessoMapper;
    
    /**
     * Converte UsuarioEntity para UsuarioDTO
     * @param usuarioEntity a entidade de usuário
     * @return UsuarioDTO correspondente
     */
    public UsuarioDTO toDTO(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }
        
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuarioEntity.getId());
        usuarioDTO.setNome(usuarioEntity.getNome());
        usuarioDTO.setEmail(usuarioEntity.getEmail());
        usuarioDTO.setUser(usuarioEntity.getUser());
        if (usuarioEntity.getAcesso() != null) {
            usuarioDTO.setAcesso(acessoMapper.toDTO(usuarioEntity.getAcesso()));
        }
        
        return usuarioDTO;
    }
    
    /**
     * Converte UsuarioDTO para UsuarioEntity
     * @param usuarioDTO o DTO de usuário
     * @return UsuarioEntity correspondente
     */
    public UsuarioEntity toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuarioDTO.getId());
        usuarioEntity.setNome(usuarioDTO.getNome());
        usuarioEntity.setEmail(usuarioDTO.getEmail());
        usuarioEntity.setUser(usuarioDTO.getUser());
        
        return usuarioEntity;
    }
} 