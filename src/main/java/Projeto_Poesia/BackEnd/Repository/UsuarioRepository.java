package Projeto_Poesia.BackEnd.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Projeto_Poesia.BackEnd.Entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);
}
