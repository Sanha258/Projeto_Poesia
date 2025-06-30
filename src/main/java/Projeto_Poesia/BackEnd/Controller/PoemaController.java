package Projeto_Poesia.BackEnd.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Projeto_Poesia.BackEnd.DTO.PoemaDTO;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Service.PoemaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/poema")
public class PoemaController {

    @Autowired
    private PoemaService poemaService;

    @PostMapping
    public ResponseEntity<?> cadastrarPoema(@RequestBody PoemaDTO poemaDTO) {
        try {
            PoemaEntity poema = poemaService.cadastrarPoema(poemaDTO);
            return ResponseEntity.ok(poema);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PoemaEntity>> listarPoemas() {
        return ResponseEntity.ok(poemaService.listarPoemas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPoemaPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(poemaService.buscarPoema(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPoema(@PathVariable Long id, @RequestBody PoemaDTO poemaDTO) {
        try {
            PoemaEntity poema = poemaService.atualizarPoema(id, poemaDTO);
            return ResponseEntity.ok(poema);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<?> listarPorAutor(@PathVariable Long autorId) {
        try {
            List<PoemaEntity> poemas = poemaService.listarPorAutor(autorId);
            if (poemas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(poemas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/autor/username/{username}")
    public ResponseEntity<?> listarPorAutorUsername(@PathVariable String username) {
        try {
            List<PoemaEntity> poemas = poemaService.listarPorAutorUsername(username);
            if (poemas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(poemas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<?> listarPorCategoria(@PathVariable Long categoriaId) {
        try {
            List<PoemaEntity> poemas = poemaService.listarPorCategoria(categoriaId);
            if (poemas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(poemas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PoemaEntity>> buscarPoema(
            @RequestParam(required = false) String titulo) {
        
        if (titulo != null) {
            return ResponseEntity.ok(poemaService.buscarPorTitulo(titulo));
        }
        
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}/{autor}")
    public ResponseEntity<?> deletarPoema(@PathVariable Long id, @PathVariable Long autor) {
        try {
            poemaService.deletarPoema(id, autor);
            return ResponseEntity.ok("Poema excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}