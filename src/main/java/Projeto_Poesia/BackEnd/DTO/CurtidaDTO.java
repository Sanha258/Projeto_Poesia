package Projeto_Poesia.BackEnd.DTO;

public class CurtidaDTO {

    private Long usuarioId;
    private Long poemaId;

    public CurtidaDTO() {
    }

    public CurtidaDTO(Long usuarioId, Long poemaId) {
        this.usuarioId = usuarioId;
        this.poemaId = poemaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPoemaId() {
        return poemaId;
    }

    public void setPoemaId(Long poemaId) {
        this.poemaId = poemaId;
    }

    
}
