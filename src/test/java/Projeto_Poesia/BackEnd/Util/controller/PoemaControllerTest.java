package Projeto_Poesia.BackEnd.Util.controller;

import Projeto_Poesia.BackEnd.Controller.PoemaController;
import Projeto_Poesia.BackEnd.DTO.PoemaDTO;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Service.PoemaService;
import Projeto_Poesia.BackEnd.Mapper.PoemaMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PoemaController.class)
public class PoemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PoemaService poemaService;

    @MockBean
    private PoemaMapper poemaMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarPoemaComSucesso() throws Exception {
        PoemaDTO dto = new PoemaDTO();
        dto.setTitulo("Teste Poema");
        PoemaEntity entity = new PoemaEntity();
        entity.setId(1L);
        entity.setTitulo("Teste Poema");

        Mockito.when(poemaService.cadastrarPoema(any(PoemaDTO.class))).thenReturn(entity);

        mockMvc.perform(post("/poema")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.titulo").value("Teste Poema"));
    }

    @Test
    void deveListarPoemas() throws Exception {
        PoemaEntity p1 = new PoemaEntity();
        p1.setId(1L);
        p1.setTitulo("Poema 1");

        PoemaEntity p2 = new PoemaEntity();
        p2.setId(2L);
        p2.setTitulo("Poema 2");

        List<PoemaEntity> poemas = Arrays.asList(p1, p2);
        Mockito.when(poemaService.listarPoemas()).thenReturn(poemas);

        mockMvc.perform(get("/poema"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void deveBuscarPoemaPorId() throws Exception {
        PoemaEntity poema = new PoemaEntity();
        poema.setId(10L);
        poema.setTitulo("Poema Teste");

        Mockito.when(poemaService.buscarPoema(10L)).thenReturn(poema);

        mockMvc.perform(get("/poema/10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(10L))
            .andExpect(jsonPath("$.titulo").value("Poema Teste"));
    }

    @Test
    void deveRetornarBadRequestQuandoNaoEncontrarPoema() throws Exception {
        Mockito.when(poemaService.buscarPoema(99L))
            .thenThrow(new IllegalArgumentException("Poema não encontrado"));

        mockMvc.perform(get("/poema/99"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Poema não encontrado"));
    }

    @Test
    void deveListarPoemasPorCategoria() throws Exception {
        PoemaEntity poema = new PoemaEntity();
        poema.setId(1L);
        poema.setTitulo("Poema Categoria");

        PoemaDTO dto = new PoemaDTO();
        dto.setTitulo("Poema Categoria");

        Mockito.when(poemaService.listarPorCategoria(5L)).thenReturn(List.of(poema));
        Mockito.when(poemaMapper.toDTO(poema)).thenReturn(dto);

        mockMvc.perform(get("/poema/categoria/5"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].titulo").value("Poema Categoria"));
    }

    @Test
    void deveRetornarNoContentQuandoCategoriaSemPoema() throws Exception {
        Mockito.when(poemaService.listarPorCategoria(10L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/poema/categoria/10"))
            .andExpect(status().isNoContent());
    }

    @Test
    void deveBuscarPorTitulo() throws Exception {
        PoemaEntity poema = new PoemaEntity();
        poema.setId(1L);
        poema.setTitulo("Amor");

        Mockito.when(poemaService.buscarPorTitulo("Amor")).thenReturn(List.of(poema));

        mockMvc.perform(get("/poema/buscar?titulo=Amor"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].titulo").value("Amor"));
    }

    @Test
    void deveDeletarPoema() throws Exception {
        Mockito.doNothing().when(poemaService).deletarPoema(1L, 2L);

        mockMvc.perform(delete("/poema/1/2"))
            .andExpect(status().isOk())
            .andExpect(content().string("Poema excluído com sucesso!"));
    }
}
