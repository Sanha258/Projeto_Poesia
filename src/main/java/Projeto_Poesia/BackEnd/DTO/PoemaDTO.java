package Projeto_Poesia.BackEnd.DTO;

public class PoemaDTO {
    
    private String titulo;
    private String conteudo;
    private Long autor;
    private Long categoria;
    
    public PoemaDTO() {
    }

    public PoemaDTO(String titulo, String conteudo, Long autor, Long categoria) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getAutor() {
        return autor;
    }

    public void setAutor(Long autor) {
        this.autor = autor;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    
    
}
