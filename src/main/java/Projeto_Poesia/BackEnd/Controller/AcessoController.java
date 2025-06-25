package Projeto_Poesia.BackEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Projeto_Poesia.BackEnd.DTO.AcessoDTO;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Service.AcessoService;

@RestController
@RequestMapping("/acesso")
public class AcessoController {
    
    @Autowired
    private AcessoService acessoService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AcessoDTO acessoDTO) {
        try {
            UsuarioEntity usuario = acessoService.autenticar(acessoDTO);
            return ResponseEntity.ok("Login realizado com sucesso! Bem-vindo, " + usuario.getNome());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 