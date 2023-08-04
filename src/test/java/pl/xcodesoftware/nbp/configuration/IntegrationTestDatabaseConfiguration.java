package pl.xcodesoftware.nbp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
@Profile("integration-testing")
public class IntegrationTestDatabaseConfiguration {

    private PostgreSQLContainer container;

    public IntegrationTestDatabaseConfiguration() {
        this.container = new PostgreSQLContainer<>("postgres:11")
                .withDatabaseName("integrationTestDB")
                .withUsername("integrationUser")
                .withPassword("integrationPassword");
        this.container.start();
    }
    
}
