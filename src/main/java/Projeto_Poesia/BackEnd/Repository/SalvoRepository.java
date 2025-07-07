package Projeto_Poesia.BackEnd.Repository;

import Projeto_Poesia.BackEnd.Entity.SalvoEntity;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;
import Projeto_Poesia.BackEnd.Entity.PoemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalvoRepository extends JpaRepository<SalvoEntity, Long> {
    List<SalvoEntity> findByUsuarioId(Long usuarioId);
    Optional<SalvoEntity> findByUsuarioIdAndPoemaId(Long usuarioId, Long poemaId);
} 