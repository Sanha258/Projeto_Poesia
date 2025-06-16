package Projeto_Poesia.BackEnd.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "acesso")
public class AcessoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(length = 64, nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public AcessoEntity() {}
    public AcessoEntity(String login, String senha, UsuarioEntity usuario) {
        this.login = login;
        this.senha = senha;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public UsuarioEntity getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}
