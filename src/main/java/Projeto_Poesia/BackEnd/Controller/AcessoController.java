package Projeto_Poesia.BackEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Projeto_Poesia.BackEnd.DTO.AcessoDTO;
import Projeto_Poesia.BackEnd.DTO.UsuarioDTO;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Service.AcessoService;
import Projeto_Poesia.BackEnd.Mapper.UsuarioMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/acesso")
public class AcessoController {
    
    @Autowired
    private AcessoService acessoService;
    
    @Autowired
    private UsuarioMapper usuarioMapper;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AcessoDTO acessoDTO) {
        try {
            UsuarioEntity usuario = acessoService.autenticar(acessoDTO);
            UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login realizado com sucesso! Bem-vindo, " + usuario.getNome());
            response.put("usuario", usuarioDTO);
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 