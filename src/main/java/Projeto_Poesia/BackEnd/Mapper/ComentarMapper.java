package Projeto_Poesia.BackEnd.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import Projeto_Poesia.BackEnd.DTO.ComentarDTO;
import Projeto_Poesia.BackEnd.Entity.ComentarEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

public class ComentarMapper {
    
    // Converte ComentarDTO para ComentarEntity (para criação)
    public static ComentarEntity toEntity(ComentarDTO dto, UsuarioEntity autor, PoemaEntity poema) {
        ComentarEntity entity = new ComentarEntity();
        entity.setTexto(dto.getTexto());
        entity.setAutor(autor);
        entity.setPoema(poema);
        return entity;
    }

    // Converte ComentarEntity para ComentarDTO (simples)
    public static ComentarDTO toDTO(ComentarEntity entity) {
        ComentarDTO dto = new ComentarDTO();
        dto.setTexto(entity.getTexto());
        dto.setAutorId(entity.getAutor().getId());
        dto.setPoemaId(entity.getPoema().getId());
        return dto;
    }

    // Converte ComentarEntity para um DTO detalhado (com mais informações)
    public static ComentarDTO toDetailedDTO(ComentarEntity entity) {
        ComentarDTO dto = toDTO(entity);
        // Adiciona campos adicionais se necessário
        return dto;
    }

    // Converte lista de entidades para lista de DTOs
    public static List<ComentarDTO> toDTOList(List<ComentarEntity> entities) {
        return entities.stream()
                .map(ComentarMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Converte lista de entidades para lista de DTOs detalhados
    public static List<ComentarDTO> toDetailedDTOList(List<ComentarEntity> entities) {
        return entities.stream()
                .map(ComentarMapper::toDetailedDTO)
                .collect(Collectors.toList());
    }

    // Atualiza uma entidade existente com dados do DTO
    public static void updateEntityFromDTO(ComentarDTO dto, ComentarEntity entity) {
        if (dto.getTexto() != null) {
            entity.setTexto(dto.getTexto());
            entity.setEditado(true);
        }
    }
}
