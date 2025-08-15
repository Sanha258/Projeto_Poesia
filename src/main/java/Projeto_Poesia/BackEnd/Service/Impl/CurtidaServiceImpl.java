package Projeto_Poesia.BackEnd.Service.Impl;

import org.springframework.stereotype.Service;

import Projeto_Poesia.BackEnd.DTO.CurtidaDTO;
import Projeto_Poesia.BackEnd.Entity.CurtidaEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Repository.CurtidaRepository;
import Projeto_Poesia.BackEnd.Repository.PoemaRepository;
import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;
import Projeto_Poesia.BackEnd.Service.CurtidaService;
import jakarta.transaction.Transactional;

@Service
public class CurtidaServiceImpl implements CurtidaService {

     private final CurtidaRepository curtidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PoemaRepository poemaRepository;
    
     public CurtidaServiceImpl(CurtidaRepository curtidaRepository, 
                            UsuarioRepository usuarioRepository,
                            PoemaRepository poemaRepository) {
        this.curtidaRepository = curtidaRepository;
        this.usuarioRepository = usuarioRepository;
        this.poemaRepository = poemaRepository;
    }

    @Override
    @Transactional
    public void CurtirPoema(CurtidaDTO curtidaDTO) {
        if (curtidaRepository.existsByUsuarioIdAndPoemaId(curtidaDTO.getUsuarioId(), curtidaDTO.getPoemaId())) {
            throw new IllegalArgumentException("Usuário já curtiu este poema");
        }

        UsuarioEntity usuario = usuarioRepository.findById(curtidaDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        PoemaEntity poema = poemaRepository.findById(curtidaDTO.getPoemaId())
                .orElseThrow(() -> new IllegalArgumentException("Poema não encontrado"));

        CurtidaEntity curtida = new CurtidaEntity(usuario, poema);
        curtidaRepository.save(curtida);
    }

    @Override
    @Transactional
    public void descurtirPoema(CurtidaDTO curtidaDTO) {
        if (!curtidaRepository.existsByUsuarioIdAndPoemaId(curtidaDTO.getUsuarioId(), curtidaDTO.getPoemaId())) {
            throw new IllegalArgumentException("Usuário não curtiu este poema");
        }
        curtidaRepository.deleteByUsuarioIdAndPoemaId(curtidaDTO.getUsuarioId(), curtidaDTO.getPoemaId());
    }

    @Override
    public boolean verificarCurtida(CurtidaDTO curtidaDTO) {
        return curtidaRepository.existsByUsuarioIdAndPoemaId(curtidaDTO.getUsuarioId(), curtidaDTO.getPoemaId());
    }

    @Override
    public int contarCurtidasPoema(Long poemaId) {
        return curtidaRepository.countByPoemaId(poemaId);
    }


    
}
