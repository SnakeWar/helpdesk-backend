package br.com.mayrcon.helpdesk.config;

import br.com.mayrcon.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public void instaciaDB() {
        this.dbService.instanciaDB();
    }
}
