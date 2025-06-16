package Projeto_Poesia.BackEnd.DTO;

public class AcessoDTO {
    
    private String senha;
    
    public AcessoDTO() {}
    public AcessoDTO(String tipoAcesso, String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
