package Projeto_Poesia.BackEnd.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto_Poesia.BackEnd.Entity.ComentarEntity;

public interface ComentarRepository extends JpaRepository <ComentarEntity, Long>{
    
    List<ComentarEntity> findByPoemaId(Long poemaId);
    List<ComentarEntity> findByAutorId(Long autorId);
    boolean existsByAutorIdAndPoemaId(Long autorId, Long poemaId);
}
