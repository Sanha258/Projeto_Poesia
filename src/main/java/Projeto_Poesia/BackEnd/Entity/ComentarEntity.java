package Projeto_Poesia.BackEnd.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentario")
public class ComentarEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String texto;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity autor;

    @ManyToOne
    @JoinColumn(name = "poema_id", nullable = false)
    private PoemaEntity poema;

    @Column(nullable = false)
    private boolean editado = false;

    @Column(name = "data_edicao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEdicao;

    public ComentarEntity() {
    }

    public ComentarEntity(String texto, UsuarioEntity autor, PoemaEntity poema, boolean editado) {
        this.texto = texto;
        this.autor = autor;
        this.poema = poema;
        this.editado = editado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UsuarioEntity getAutor() {
        return autor;
    }

    public void setAutor(UsuarioEntity autor) {
        this.autor = autor;
    }

    public PoemaEntity getPoema() {
        return poema;
    }

    public void setPoema(PoemaEntity poema) {
        this.poema = poema;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public LocalDateTime getDataEdicao() {
    return dataEdicao;
    }

    public void setDataEdicao(LocalDateTime dataEdicao) {
        this.dataEdicao = dataEdicao;
    }
}
