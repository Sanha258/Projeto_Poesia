package Projeto_Poesia.BackEnd.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Projeto_Poesia.BackEnd.DTO.CategoriaDTO;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;
import Projeto_Poesia.BackEnd.Service.CategoriaService;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> salvarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            CategoriaEntity categoria = categoriaService.cadastrarCategoria(categoriaDTO);
            // Retornar o DTO em vez da Entity
            CategoriaDTO responseDTO = new CategoriaDTO();
            responseDTO.setNome(categoria.getNome());
            responseDTO.setUsuarioId(categoria.getUsuario().getId());
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategoria(){
        List<CategoriaEntity> categorias = categoriaService.listarCategorias();
        // Converter Entity para DTO
        List<CategoriaDTO> dtos = categorias.stream().map(c -> {
            CategoriaDTO dto = new CategoriaDTO();
            dto.setNome(c.getNome());
            dto.setUsuarioId(c.getUsuario().getId());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoriaPorId(@PathVariable Long id){
        try {
            CategoriaEntity categoria = categoriaService.buscarCategoriaPorId(id);
            CategoriaDTO dto = new CategoriaDTO();
            dto.setNome(categoria.getNome());
            dto.setUsuarioId(categoria.getUsuario().getId());
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/{usuarioId}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id, @PathVariable Long usuarioId){
        try {
            categoriaService.deletarCategoria(id, usuarioId);
            return ResponseEntity.ok("Categoria excluída com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
