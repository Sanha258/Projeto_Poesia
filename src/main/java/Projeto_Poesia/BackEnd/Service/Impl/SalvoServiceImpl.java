package Projeto_Poesia.BackEnd.Service.Impl;

import Projeto_Poesia.BackEnd.Entity.SalvoEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Repository.SalvoRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Repository.PoemaRepository;
import Projeto_Poesia.BackEnd.Service.SalvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalvoServiceImpl implements SalvoService {
    @Autowired
    private SalvoRepository salvoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PoemaRepository poemaRepository;

    @Override
    public SalvoEntity salvar(Long usuarioId, Long poemaId) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<PoemaEntity> poemaOpt = poemaRepository.findById(poemaId);
        if (usuarioOpt.isEmpty() || poemaOpt.isEmpty()) {
            throw new RuntimeException("Usuário ou poema não encontrado");
        }
        if (salvoRepository.findByUsuarioIdAndPoemaId(usuarioId, poemaId).isPresent()) {
            throw new RuntimeException("Poesia já salva para este usuário");
        }
        SalvoEntity salvo = new SalvoEntity(usuarioOpt.get(), poemaOpt.get());
        return salvoRepository.save(salvo);
    }

    @Override
    public void remover(Long usuarioId, Long poemaId) {
        Optional<SalvoEntity> salvoOpt = salvoRepository.findByUsuarioIdAndPoemaId(usuarioId, poemaId);
        if (salvoOpt.isEmpty()) {
            throw new RuntimeException("Poesia não está salva para este usuário");
        }
        salvoRepository.delete(salvoOpt.get());
    }

    @Override
    public List<SalvoEntity> listarPorUsuario(Long usuarioId) {
        return salvoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public boolean poemaSalvo(Long usuarioId, Long poemaId) {
        return salvoRepository.findByUsuarioIdAndPoemaId(usuarioId, poemaId).isPresent();
    }
} 