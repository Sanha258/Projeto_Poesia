package Projeto_Poesia.BackEnd.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import Projeto_Poesia.BackEnd.DTO.ComentarDTO;
import Projeto_Poesia.BackEnd.Entity.ComentarEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

@Component
public class ComentarMapper {
    
    public ComentarEntity toEntity(ComentarDTO dto, UsuarioEntity autor, PoemaEntity poema) {
        if (dto == null || autor == null || poema == null) {
            return null;
        }
        
        ComentarEntity entity = new ComentarEntity();
        entity.setTexto(dto.getTexto());
        entity.setAutor(autor);
        entity.setPoema(poema);
        return entity;
    }

    public ComentarDTO toDTO(ComentarEntity entity) {
        if (entity == null) {
            return null;
        }
        
        ComentarDTO dto = new ComentarDTO();
        dto.setTexto(entity.getTexto());
        dto.setAutorId(entity.getAutor() != null ? entity.getAutor().getId() : null);
        dto.setPoemaId(entity.getPoema() != null ? entity.getPoema().getId() : null);
        return dto;
    }

    public List<ComentarDTO> toDTOList(List<ComentarEntity> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDTO(ComentarDTO dto, ComentarEntity entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        if (dto.getTexto() != null) {
            entity.setTexto(dto.getTexto());
            entity.setEditado(true);
        }
    }
}
