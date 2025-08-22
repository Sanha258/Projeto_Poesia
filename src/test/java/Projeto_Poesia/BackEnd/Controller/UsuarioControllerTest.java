package Projeto_Poesia.BackEnd.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
// aplicaton-test.proprieties || aplication-config.properties se @ActiveProfiles("config")
@ActiveProfiles("test")
class UsuarioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve listar os usuários criados, incluindo o recém criado")
    void deveListarUsuarios() throws Exception {
        // garante ao menos um registro
        if (usuarioRepository.count() == 0) {
            mockMvc.perform(post("/cadastro")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nome\":\"Liam\",\"email\":\"liam@gmail\",\"user\":\"liamCabral\",\"acesso\":{\"senha\":\"liam1234\"}}"))
            .andExpect(status().isCreated());

            mockMvc.perform(get("/cadastro")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0.id]").exists());
        }
    }

}
