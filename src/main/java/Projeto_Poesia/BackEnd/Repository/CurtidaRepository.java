package Projeto_Poesia.BackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto_Poesia.BackEnd.Entity.CurtidaEntity;

public interface CurtidaRepository extends JpaRepository<CurtidaEntity, Long>{

    boolean existsByUsuarioIdAndPoemaId (Long usuarioId, Long poemaId);
    void deleteByUsuarioIdAndPoemaId (Long usuarioId, Long poemaId);
    int countByPoemaId(Long poemaId);

}
