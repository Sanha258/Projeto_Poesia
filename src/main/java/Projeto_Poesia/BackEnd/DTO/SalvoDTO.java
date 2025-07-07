package Projeto_Poesia.BackEnd.DTO;

import java.time.LocalDateTime;

public class SalvoDTO {
    private Long id;
    private Long usuarioId;
    private Long poemaId;
    private String poemaTitulo;
    private String poemaConteudo;
    private String poemaCategoria;
    private String poemaAutor;
    private LocalDateTime poemaData;

    public SalvoDTO() {}

    public SalvoDTO(Long id, Long usuarioId, Long poemaId, String poemaTitulo, String poemaConteudo, String poemaCategoria, String poemaAutor, LocalDateTime poemaData) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.poemaId = poemaId;
        this.poemaTitulo = poemaTitulo;
        this.poemaConteudo = poemaConteudo;
        this.poemaCategoria = poemaCategoria;
        this.poemaAutor = poemaAutor;
        this.poemaData = poemaData;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPoemaId() { return poemaId; }
    public void setPoemaId(Long poemaId) { this.poemaId = poemaId; }

    public String getPoemaTitulo() { return poemaTitulo; }
    public void setPoemaTitulo(String poemaTitulo) { this.poemaTitulo = poemaTitulo; }

    public String getPoemaConteudo() { return poemaConteudo; }
    public void setPoemaConteudo(String poemaConteudo) { this.poemaConteudo = poemaConteudo; }

    public String getPoemaCategoria() { return poemaCategoria; }
    public void setPoemaCategoria(String poemaCategoria) { this.poemaCategoria = poemaCategoria; }

    public String getPoemaAutor() { return poemaAutor; }
    public void setPoemaAutor(String poemaAutor) { this.poemaAutor = poemaAutor; }

    public LocalDateTime getPoemaData() { return poemaData; }
    public void setPoemaData(LocalDateTime poemaData) { this.poemaData = poemaData; }
} 