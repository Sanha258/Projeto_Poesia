package Projeto_Poesia.BackEnd.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
// aplicaton-test.proprieties || aplication-config.properties se @ActiveProfiles("config")
@ActiveProfiles("test")
class UsuarioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve cadastrar o usuário com sucesso")
    void deveCadastrarUsuario() throws Exception{
        
    }

}
