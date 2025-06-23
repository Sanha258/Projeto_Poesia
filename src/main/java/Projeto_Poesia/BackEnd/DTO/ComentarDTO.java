package Projeto_Poesia.BackEnd.DTO;

public class ComentarDTO {
    
    private String texto;
    private Long autorId;
    private Long poemaId;
    
    public ComentarDTO() {
    }

    public ComentarDTO(String texto, Long autorId, Long poemaId) {
        this.texto = texto;
        this.autorId = autorId;
        this.poemaId = poemaId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getPoemaId() {
        return poemaId;
    }

    public void setPoemaId(Long poemaId) {
        this.poemaId = poemaId;
    }

    
}
