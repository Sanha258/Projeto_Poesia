package Projeto_Poesia.BackEnd.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Projeto_Poesia.BackEnd.Entity.AcessoEntity;

public interface AcessoRepository extends JpaRepository<AcessoEntity, Long>{
    
    Optional<AcessoEntity> findByLoginAndSenha(String login, String senha);
}
