package Projeto_Poesia.BackEnd.Util.Login;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidarEmailTest {

    String emailValido = "william@gmail.com";
    String emailInvalido = "teste.email";
    String emailNull = null;
    String emailVazio = "";
    String emailSemArroba = "testeemail.com";
    String emailSemPonto = "teste@testecom";

  ValidarEmail validarEmail = new ValidarEmail();

    @Test
    void validarEmailCadastrado() {
        assertTrue(validarEmail.validarEmailCadastrado(emailValido), "Email valido tem @ e ponto");
        
    }

    @Test
    void validarEmailInvalido() {   
        assertFalse(validarEmail.validarEmailCadastrado(emailInvalido), "Email invalido não tem @ ");
    }

    @Test
    void validarEmailNull() {
        assertFalse(validarEmail.validarEmailCadastrado(emailNull), "Email não é válido, preencha o campo");
    }

    @Test
    void validarEmailVazio() {
        assertFalse(validarEmail.validarEmailCadastrado(emailVazio), "Email não é válido, preencha o campo");
    }

    

    
}
