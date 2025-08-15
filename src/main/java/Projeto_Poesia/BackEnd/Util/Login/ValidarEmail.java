package Projeto_Poesia.BackEnd.Util.Login;

public class ValidarEmail {
    
    public boolean validarEmailCadastrado(String email) {
        return email != null && email.contains("@") && email.contains(".");
    } 
    
}
