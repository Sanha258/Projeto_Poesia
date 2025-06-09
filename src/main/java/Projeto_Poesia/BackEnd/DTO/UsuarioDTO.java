package Projeto_Poesia.BackEnd.DTO;

public class UsuarioDTO {

    private Long id;
    private String userName;
    private String nome;
    private String email;
    private String cep;


    public UsuarioDTO() {
    }


    public UsuarioDTO(Long id, String userName, String nome, String email, String cep) {
        this.id = id;
        this.userName = userName;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
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


    public String getCep() {
        return cep;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }

    

    
}
