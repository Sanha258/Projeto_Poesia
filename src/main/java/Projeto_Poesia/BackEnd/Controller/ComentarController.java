package Projeto_Poesia.BackEnd.Controller;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import Projeto_Poesia.BackEnd.DTO.ComentarDTO;
import Projeto_Poesia.BackEnd.Entity.ComentarEntity;
import Projeto_Poesia.BackEnd.Service.ComentarService;

@RestController
@RequestMapping("/comentarios")
public class ComentarController {
  
    private final ComentarService comentarService;

    @Autowired
    public ComentarController(ComentarService comentarService) {
        this.comentarService = comentarService;
    }

    @PostMapping
    public ResponseEntity<ComentarEntity> criarComentario(@RequestBody ComentarDTO comentarDTO) {
        ComentarEntity comentario = comentarService.criarComentario(comentarDTO);
        return ResponseEntity.ok(comentario);  // Retorna 200 OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editarComentario(
        @PathVariable Long id, 
        @RequestBody ComentarDTO comentarDTO) {

        ComentarEntity comentario = comentarService.editarComentario(id, comentarDTO);
        
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", comentario.getId());
        response.put("texto", comentario.getTexto());
        response.put("editado", comentario.isEditado());
        
        // Autor
        Map<String, Object> autorMap = new LinkedHashMap<>();
        autorMap.put("id", comentario.getAutor().getId());
        autorMap.put("nome", comentario.getAutor().getNome());
        response.put("autor", autorMap);
        
        // Poema (adicionado)
        Map<String, Object> poemaMap = new LinkedHashMap<>();
        poemaMap.put("id", comentario.getPoema().getId());
        poemaMap.put("titulo", comentario.getPoema().getTitulo());
        response.put("poema", poemaMap);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarComentario(
            @PathVariable Long id,
            @RequestParam Long autorId) {
        
        try {
            comentarService.deletarComentario(id, autorId);
            return ResponseEntity.ok(Map.of("message", "Comentário deletado com sucesso"));
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/poema/{poemaId}")
    public ResponseEntity<List<Map<String, Object>>> listarPorPoema(@PathVariable Long poemaId) {
        List<ComentarEntity> comentarios = comentarService.listarPorPoema(poemaId);
        
        List<Map<String, Object>> response = comentarios.stream().map(comentario -> {
            Map<String, Object> comentarioMap = new LinkedHashMap<>();
            comentarioMap.put("id", comentario.getId());
            comentarioMap.put("texto", comentario.getTexto());
            comentarioMap.put("dataCriacao", comentario.getDataCriacao());
            comentarioMap.put("editado", comentario.isEditado());
            
            Map<String, Object> autorMap = new LinkedHashMap<>();
            autorMap.put("id", comentario.getAutor().getId());
            autorMap.put("nome", comentario.getAutor().getNome());
            comentarioMap.put("autor", autorMap);
            
            return comentarioMap;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Map<String, Object>>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<ComentarEntity> comentarios = comentarService.listarPorUsuario(usuarioId);
        
        List<Map<String, Object>> response = comentarios.stream().map(comentario -> {
            Map<String, Object> comentarioMap = new LinkedHashMap<>();
            comentarioMap.put("id", comentario.getId());
            comentarioMap.put("texto", comentario.getTexto());
            comentarioMap.put("dataCriacao", comentario.getDataCriacao());
            comentarioMap.put("editado", comentario.isEditado());
            
            // Dados resumidos do poema
            Map<String, Object> poemaMap = new LinkedHashMap<>();
            poemaMap.put("id", comentario.getPoema().getId());
            poemaMap.put("titulo", comentario.getPoema().getTitulo());
            comentarioMap.put("poema", poemaMap);
            
            return comentarioMap;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

}
