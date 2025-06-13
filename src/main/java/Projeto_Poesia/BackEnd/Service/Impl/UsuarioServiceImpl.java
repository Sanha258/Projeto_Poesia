package Projeto_Poesia.BackEnd.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.UsuarioDTO;
import Projeto_Poesia.BackEnd.Entity.AcessoEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Repository.AcessoRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Service.UsuarioService;
import Projeto_Poesia.BackEnd.Service.util.HashUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AcessoRepository acessoRepository;

    @Override
    public UsuarioEntity cadastrarUsuario(UsuarioDTO usuarioDTO){
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        String senhaHash = HashUtil.gerarHashSHA256(usuarioDTO.getAcesso().getSenha());

        UsuarioEntity usuario = new UsuarioEntity(
            usuarioDTO.getNome(),
            usuarioDTO.getEmail(),
            usuarioDTO.getUser()
        );

        AcessoEntity acesso = new AcessoEntity(
            "USER",
            senhaHash,
            usuario
        );

        usuario.setAcesso(acesso);

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioSalvo;
    }

    public void deletarUsuario(Long id){
        
    }

}
