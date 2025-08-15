package Projeto_Poesia.BackEnd.DTO;

public class CategoriaDTO {
    
    private Long id;
    private String nome;
    private Long usuarioId;

    public CategoriaDTO() {}
    public CategoriaDTO(Long id, String nome, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.usuarioId = usuarioId;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

}
