package Projeto_Poesia.BackEnd.Repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Projeto_Poesia.BackEnd.Entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNomeIgnoreCase(String nome);
    //List<CategoriaEntity> findByUsuarioId(Long usuarioId);//buscar por usuário
} 
