package Projeto_Poesia.BackEnd.Util.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import Projeto_Poesia.BackEnd.Repository.PoemaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PoemaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PoemaRepository repository;

    @Test
    @DisplayName("Deve cadastrar poema com sucesso")
    void deveCadastrarPoema() throws Exception {
        String tituloGerado = "flor" + System.currentTimeMillis();
        String json = "{"
            + "\"titulo\":\"" + tituloGerado + "\","
            + "\"conteudo\":\"minha flor\","
            + "\"data\":\"14/08/2025 00:00\","
            + "\"autor\":{\"id\":1},"
            + "\"categoria\":{\"id\":1}"
            + "}";


        mockMvc.perform(post("/poema")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"))
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.titulo").value(tituloGerado))
            .andExpect(jsonPath("$.conteudo").value("minha flor"))
            .andExpect(jsonPath("$.data").value("14/08/2025 00:00"))
            .andExpect(jsonPath("$.autor.id").value(1))
            .andExpect(jsonPath("$.categoria.id").value(1));

            
    }  

    /*@Test
    @DisplayName("Deve listar poemas incluindo recém criado")
    void deveListarPoemas() throws Exception {
        if(repository.count() == 0) {
            mockMvc.perform(post("/poema")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{"
                        + "\"titulo\":\"flor de verão\","
                        + "\"conteudo\":\"minha flor\","
                        + "\"autor\":\"1\","
                        + "\"categoria\":\"romantica\""
                        + "}"))
                    .andExpect(status().isCreated());
        }

        mockMvc.perform(get("/poema")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }*/
}
