package Projeto_Poesia.BackEnd.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "curtida", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "poema_id"}))
public class CurtidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "poema_id", nullable = false)
    private PoemaEntity poema;
    
    @Column(nullable= false)
    private LocalDateTime dataCurtida = LocalDateTime.now();

    public CurtidaEntity() {
    }

    public CurtidaEntity(UsuarioEntity usuario, PoemaEntity poema) {
        this.usuario = usuario;
        this.poema = poema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PoemaEntity getPoema() {
        return poema;
    }

    public void setPoema(PoemaEntity poema) {
        this.poema = poema;
    }

    public LocalDateTime getDataCurtida() {
        return dataCurtida;
    }

    public void setDataCurtida(LocalDateTime dataCurtida) {
        this.dataCurtida = dataCurtida;
    }

    
}
