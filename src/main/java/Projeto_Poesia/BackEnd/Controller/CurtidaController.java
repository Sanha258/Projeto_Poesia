package Projeto_Poesia.BackEnd.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projeto_Poesia.BackEnd.DTO.CurtidaDTO;
import Projeto_Poesia.BackEnd.Service.CurtidaService;


@RestController
@RequestMapping("/curtidas")
@CrossOrigin(origins = "*")
public class CurtidaController {

    private final CurtidaService curtidaService;

    public CurtidaController(CurtidaService curtidaService) {
        this.curtidaService = curtidaService;
    }

    @PostMapping
    public ResponseEntity<?> curtirPoema(@RequestBody CurtidaDTO curtidaDTO) {
        try {
            curtidaService.CurtirPoema(curtidaDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> descurtirPoema(@RequestBody CurtidaDTO curtidaDTO) {
        try {
            curtidaService.descurtirPoema(curtidaDTO);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/verificar")
    public ResponseEntity<Boolean> verificarCurtida(@RequestBody CurtidaDTO curtidaDTO) {
        return ResponseEntity.ok(curtidaService.verificarCurtida(curtidaDTO));
    }

    @GetMapping("/contar/{poemaId}")
    public ResponseEntity<Integer> contarCurtidasPoema(@PathVariable Long poemaId) {
        return ResponseEntity.ok(curtidaService.contarCurtidasPoema(poemaId));
    }

    
}
