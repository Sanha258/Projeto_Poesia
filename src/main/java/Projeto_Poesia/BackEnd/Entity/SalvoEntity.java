package Projeto_Poesia.BackEnd.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "salvo")
public class SalvoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "poema_id", nullable = false)
    private PoemaEntity poema;

    public SalvoEntity() {}

    public SalvoEntity(UsuarioEntity usuario, PoemaEntity poema) {
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
} 