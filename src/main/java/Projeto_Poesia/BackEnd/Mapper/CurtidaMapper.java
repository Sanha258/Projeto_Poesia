package Projeto_Poesia.BackEnd.Mapper;

import org.springframework.stereotype.Component;

import Projeto_Poesia.BackEnd.DTO.CurtidaDTO;
import Projeto_Poesia.BackEnd.Entity.CurtidaEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

@Component
public class CurtidaMapper {
   
     public CurtidaDTO toDTO(CurtidaEntity curtidaEntity) {
        if (curtidaEntity == null) {
            return null;
        }
        
        CurtidaDTO dto = new CurtidaDTO();
        if (curtidaEntity.getUsuario() != null) {
            dto.setUsuarioId(curtidaEntity.getUsuario().getId());
        }
        if (curtidaEntity.getPoema() != null) {
            dto.setPoemaId(curtidaEntity.getPoema().getId());
        }
        return dto;
    }

    public CurtidaEntity toEntity(CurtidaDTO curtidaDTO, UsuarioEntity usuario, PoemaEntity poema) {
        if (curtidaDTO == null || usuario == null || poema == null) {
            return null;
        }
        
        CurtidaEntity entity = new CurtidaEntity();
        entity.setUsuario(usuario);
        entity.setPoema(poema);
        return entity;
    }
}
