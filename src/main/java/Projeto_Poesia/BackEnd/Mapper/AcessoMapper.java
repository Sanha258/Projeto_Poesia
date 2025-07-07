package Projeto_Poesia.BackEnd.Mapper;

import org.springframework.stereotype.Component;
import Projeto_Poesia.BackEnd.DTO.AcessoDTO;
import Projeto_Poesia.BackEnd.Entity.AcessoEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

@Component
public class AcessoMapper {
    public AcessoDTO toDTO(AcessoEntity entity) {
        if (entity == null) return null;
        return new AcessoDTO(null, entity.getSenha());
    }

    public AcessoEntity toEntity(AcessoDTO dto, UsuarioEntity usuario) {
        if (dto == null) return null;
        return new AcessoEntity(usuario.getEmail(), dto.getSenha(), usuario);
    }
} 