package Projeto_Poesia.BackEnd.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResponseTimeHealthIndicator implements HealthIndicator{
    
    @Override
    public Health health() {

        String url = "https://github.com";
        //Cria uma instância do RestTemplate, que é um cliente HTTP síncrono usado para fazer requisição
        RestTemplate restTemplate = new RestTemplate();
        long start = System.currentTimeMillis();

        try {
            //Realiza uma requisição HTTP GET para a URL específica e obtem a resposta como String
            restTemplate.getForObject(url, String.class);
            long elapsed = System.currentTimeMillis() - start;
            String qualidade;
            if (elapsed < 200) {
                qualidade = "ótimo";
            } else if (elapsed < 400) {
                qualidade = "bom";
            } else {
                qualidade = "ruim";
            }
        
            return Health.up()
            .withDetail("API Externa", url)
            .withDetail("Tempo de resposta(ms)", elapsed)
            .withDetail("Classificação", qualidade)
            .withDetail("Status", "API externa OK")
            .withDetail("Origem", "API")
            .build();
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - start;

            return Health.down(e)
            .withDetail("API Externa", url)
            .withDetail("Tempo de Resposta(ms)", elapsed)
            .withDetail("Status", "Falha ao acessar API externa")
            .withDetail("Origem", "API")
            .build();
        }
    }

}
