package Projeto_Poesia.BackEnd.Service.Impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Projeto_Poesia.BackEnd.DTO.UsuarioDTO;
import Projeto_Poesia.BackEnd.Entity.AcessoEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Repository.AcessoRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Service.UsuarioService;
import Projeto_Poesia.BackEnd.Service.util.HashUtil;
import Projeto_Poesia.BackEnd.Mapper.UsuarioMapper;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AcessoRepository acessoRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UsuarioEntity cadastrarUsuario(UsuarioDTO usuarioDTO){
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        String senhaHash = HashUtil.gerarHashSHA256(usuarioDTO.getAcesso().getSenha());

        UsuarioEntity usuario = usuarioMapper.toEntity(usuarioDTO);

        AcessoEntity acesso = new AcessoEntity(
            usuario.getEmail(),
            senhaHash,
            usuario
        );

        usuario.setAcesso(acesso);

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioSalvo;
    }

    @Override
    public List<UsuarioEntity> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioEntity> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    @Override
    public UsuarioEntity atualizarUsuario(Long id, UsuarioDTO usuarioDTO){
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com o Id: " + id);
        }

        UsuarioEntity usuario = optionalUsuario.get();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUser(usuarioDTO.getUser());

        return usuarioRepository.save(usuario);
    }

    @Override
    public void deletarUsuario(Long id){
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            UsuarioEntity usuario = optionalUsuario.get();
            if (usuario.getAcesso() != null) {
                acessoRepository.delete(usuario.getAcesso());
            }
            usuarioRepository.delete(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado com o Id: " + id);
        }
    }

}
