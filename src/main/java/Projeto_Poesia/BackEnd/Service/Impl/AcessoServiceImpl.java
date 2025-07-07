package Projeto_Poesia.BackEnd.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Projeto_Poesia.BackEnd.DTO.AcessoDTO;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Repository.AcessoRepository;
import Projeto_Poesia.BackEnd.Service.AcessoService;
import Projeto_Poesia.BackEnd.Service.util.HashUtil;

@Service
public class AcessoServiceImpl implements AcessoService {
    
    @Autowired
    private AcessoRepository acessoRepository;
    
    @Override
    public UsuarioEntity autenticar(AcessoDTO acessoDTO) {
        String senhaHash = HashUtil.gerarHashSHA256(acessoDTO.getSenha());
        
        return acessoRepository.findByLoginAndSenha(acessoDTO.getLogin(), senhaHash)
            .map(acesso -> acesso.getUsuario())
            .orElseThrow(() -> new IllegalArgumentException("Login ou senha inválidos"));
    }
} 