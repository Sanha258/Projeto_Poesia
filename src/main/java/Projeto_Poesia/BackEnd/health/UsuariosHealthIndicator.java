package Projeto_Poesia.BackEnd.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import Projeto_Poesia.BackEnd.Repository.UsuarioRepository;

@Component
public class UsuariosHealthIndicator implements HealthIndicator{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public Health health(){
        try {
            //Usa JPA para testar acesso ao banco
            long count = usuarioRepository.count();
            return Health.up()
            .withDetail("Aplicação", "Usuários API está saudável")
            .withDetail("Banco de Dados", "OK")
            .withDetail("Origem", "API")
            .withDetail("Quantidade de usuários", count)
            .build();
        } catch (Exception e) {
            return Health.down(e)
            .withDetail("Aplicação", "Erro ao acessar o banco de dados")
            .withDetail("Origem", "API")
            .build();
        }
    }

}
