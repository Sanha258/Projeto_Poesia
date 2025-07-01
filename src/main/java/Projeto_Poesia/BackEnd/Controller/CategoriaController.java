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
import Projeto_Poesia.BackEnd.Mapper.CategoriaMapper;
import Projeto_Poesia.BackEnd.Service.CategoriaService;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaMapper categoriaMapper;

    @PostMapping
    public ResponseEntity<?> salvarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            CategoriaEntity categoria = categoriaService.cadastrarCategoria(categoriaDTO);
            CategoriaDTO categoriaRetorno = categoriaMapper.toDTO(categoria);
            return ResponseEntity.ok(categoriaRetorno);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategoria() {
        List<CategoriaEntity> categorias = categoriaService.listarCategorias();
        List<CategoriaDTO> categoriasDTO = categorias.stream()
            .map(categoriaMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(categoriasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoriaPorId(@PathVariable Long id) {
        try {
            CategoriaEntity categoria = categoriaService.buscarCategoriaPorId(id);
            CategoriaDTO categoriaDTO = categoriaMapper.toDTO(categoria);
            return ResponseEntity.ok(categoriaDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/{usuarioId}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id, @PathVariable Long usuarioId) {
        try {
            categoriaService.deletarCategoria(id, usuarioId);
            return ResponseEntity.ok("Categoria excluída com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
