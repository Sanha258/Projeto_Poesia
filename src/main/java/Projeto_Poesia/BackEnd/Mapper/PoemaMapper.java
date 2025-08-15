package Projeto_Poesia.BackEnd.Mapper;

import org.springframework.stereotype.Component;
import Projeto_Poesia.BackEnd.DTO.PoemaDTO;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;

@Component
public class PoemaMapper {
    public PoemaDTO toDTO(PoemaEntity poemaEntity) {
        if (poemaEntity == null) {
            return null;
        }
        PoemaDTO dto = new PoemaDTO();
        dto.setTitulo(poemaEntity.getTitulo());
        dto.setConteudo(poemaEntity.getConteudo());
        if (poemaEntity.getAutor() != null) {
            dto.setAutor(poemaEntity.getAutor().getId());
        }
        if (poemaEntity.getCategoria() != null) {
            dto.setCategoria(poemaEntity.getCategoria().getId());
        }
        return dto;
    }

    public PoemaEntity toEntity(PoemaDTO poemaDTO, UsuarioEntity autor, CategoriaEntity categoria) {
        if (poemaDTO == null || autor == null || categoria == null) {
            return null;
        }
        PoemaEntity entity = new PoemaEntity();
        entity.setTitulo(poemaDTO.getTitulo());
        entity.setConteudo(poemaDTO.getConteudo());
        entity.setAutor(autor);
        entity.setCategoria(categoria);
        return entity;
    }
} 