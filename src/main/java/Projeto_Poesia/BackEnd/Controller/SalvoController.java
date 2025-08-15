package Projeto_Poesia.BackEnd.Controller;

import Projeto_Poesia.BackEnd.DTO.SalvoDTO;
import Projeto_Poesia.BackEnd.Entity.SalvoEntity;
import Projeto_Poesia.BackEnd.Service.SalvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/salvos")
public class SalvoController {
    @Autowired
    private SalvoService salvoService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestParam Long usuarioId, @RequestParam Long poemaId) {
        try {
            SalvoEntity salvo = salvoService.salvar(usuarioId, poemaId);
            return ResponseEntity.ok(toDTO(salvo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> remover(@RequestParam Long usuarioId, @RequestParam Long poemaId) {
        try {
            salvoService.remover(usuarioId, poemaId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<SalvoDTO> salvos = salvoService.listarPorUsuario(usuarioId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
            return ResponseEntity.ok(salvos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar poesias salvas");
        }
    }

    @GetMapping("/verifica")
    public ResponseEntity<?> poemaSalvo(@RequestParam Long usuarioId, @RequestParam Long poemaId) {
        try {
            boolean isSalvo = salvoService.poemaSalvo(usuarioId, poemaId);
            return ResponseEntity.ok(isSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao verificar se poema está salvo");
        }
    }

    private SalvoDTO toDTO(SalvoEntity salvo) {
        return new SalvoDTO(
            salvo.getId(),
            salvo.getUsuario().getId(),
            salvo.getPoema().getId(),
            salvo.getPoema().getTitulo(),
            salvo.getPoema().getConteudo(),
            salvo.getPoema().getCategoria() != null ? salvo.getPoema().getCategoria().getNome() : null,
            salvo.getPoema().getAutor() != null ? salvo.getPoema().getAutor().getUser() : null,
            salvo.getPoema().getData()
        );
    }
} 