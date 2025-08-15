package Projeto_Poesia.BackEnd.DTO;

public class AcessoDTO {
    
    private String login;
    private String senha;
    
    public AcessoDTO() {}
    public AcessoDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
