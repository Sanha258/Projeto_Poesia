package Projeto_Poesia.BackEnd.Health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import Projeto_Poesia.BackEnd.Repository.PoemaRepository;

@Component
public class PoemaHealthIndicator implements HealthIndicator {

    @Autowired
    private PoemaRepository poemaRepository;

    @Override
    public Health health () {

        try {
            long count = poemaRepository.count();
        return Health.up()
            .withDetail("Aplicação", "Poema API está Saudavel")
            .withDetail("Versão", "1.0.0")
            .withDetail("Quantidade de Poemas", count)
            .withDetail("Banco de dados", "ok")
            .withDetail("Origem", "API")
            .build();
        } catch (Exception e) {
        return Health.down(e)
             .withDetail("Aplicação", "Poema API está Saudavel")
             .withDetail("Origem", "API")
             .build();
        }

    }
    
}
