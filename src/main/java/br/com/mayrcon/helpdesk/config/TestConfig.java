package br.com.mayrcon.helpdesk.config;

import br.com.mayrcon.helpdesk.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    private DBService dbService;

    @Bean
    public void instaciaDB() {
        this.dbService.instanciaDB();
    }
}
