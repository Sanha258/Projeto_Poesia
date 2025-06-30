package Projeto_Poesia.BackEnd.Mapper;

import org.springframework.stereotype.Component;
import Projeto_Poesia.BackEnd.DTO.CategoriaDTO;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

@Component
public class CategoriaMapper {
    public CategoriaDTO toDTO(CategoriaEntity categoriaEntity) {
        if (categoriaEntity == null) {
            return null;
        }
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNome(categoriaEntity.getNome());
        if (categoriaEntity.getUsuario() != null) {
            dto.setUsuarioId(categoriaEntity.getUsuario().getId());
        }
        return dto;
    }

    public CategoriaEntity toEntity(CategoriaDTO categoriaDTO, UsuarioEntity usuario) {
        if (categoriaDTO == null || usuario == null) {
            return null;
        }
        CategoriaEntity entity = new CategoriaEntity();
        entity.setNome(categoriaDTO.getNome());
        entity.setUsuario(usuario);
        return entity;
    }
} 