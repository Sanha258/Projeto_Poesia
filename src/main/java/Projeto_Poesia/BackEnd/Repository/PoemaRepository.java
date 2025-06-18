package Projeto_Poesia.BackEnd.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Projeto_Poesia.BackEnd.Entity.PoemaEntity;

public interface PoemaRepository extends JpaRepository <PoemaEntity, Long>{

    // Busca poemas por autor (usuário)
    List<PoemaEntity> findByAutorId(Long autorId);
    List<PoemaEntity> findByCategoriaId(Long categoriaId);
    List<PoemaEntity> findByTituloContainingIgnoreCaseAndAutorId(String titulo, Long autor);

    // Busca poemas por título (contendo o texto, case insensitive)
    List<PoemaEntity> findByTituloContainingIgnoreCase(String titulo); 

    // Busca poemas por conteúdo (contendo o texto)
    @Query("SELECT p FROM PoemaEntity p WHERE p.conteudo LIKE %:termo%")
    List<PoemaEntity> buscarPorConteudo(@Param("termo") String termo);

    // Busca os últimos 10 poemas ordenados por data (para home)
    List<PoemaEntity> findTop10ByOrderByDataDesc();

   
    
}
