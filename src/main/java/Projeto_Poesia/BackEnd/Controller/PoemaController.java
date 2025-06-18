package Projeto_Poesia.BackEnd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projeto_Poesia.BackEnd.DTO.PoemaDTO;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Service.PoemaService;

@RestController
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
