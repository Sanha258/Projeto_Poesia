package Projeto_Poesia.BackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
}
