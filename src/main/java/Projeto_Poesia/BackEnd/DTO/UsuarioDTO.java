package Projeto_Poesia.BackEnd.DTO;

public class UsuarioDTO {

    private String nome;
    private String email;
    private String user;
    private AcessoDTO acesso;
    
    public UsuarioDTO() {}
    public UsuarioDTO(String nome, String email, String user, AcessoDTO acesso) {
        this.nome = nome;
        this.email = email;
        this.user = user;
        this.acesso = acesso;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    
    public AcessoDTO getAcesso() {
        return acesso;
    }
    public void setAcesso(AcessoDTO acesso) {
        this.acesso = acesso;
    }

}
