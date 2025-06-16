package Projeto_Poesia.BackEnd.DTO;

public class CategoriaDTO {
    
    private String nome;
    private Long usuarioId;

    public CategoriaDTO() {}
    public CategoriaDTO(String nome, Long usuarioId) {
        this.nome = nome;
        this.usuarioId = usuarioId;
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
