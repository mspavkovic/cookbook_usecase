package com.valcon.cookbook.cucumber;

import java.lang.reflect.Type;
import java.time.Duration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valcon.cookbook.util.TestDbUtils;

import io.cucumber.java.Before;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;

@CucumberContextConfiguration
@Slf4j
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberConfig {

    static final long DB_STARTUP_TIMEOUT = 60L;

    static PostgreSQLContainer<?> postgreSQLTestContainer = new PostgreSQLContainer<>("postgres:10.4")
            .withUsername("testUsername")
            .withPassword("testPassword")
            .withDatabaseName("aureus")
            .withStartupTimeout(Duration.ofSeconds(DB_STARTUP_TIMEOUT));

    @Autowired
    private TestDbUtils testDbUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        postgreSQLTestContainer.start();
        Awaitility.waitAtMost(Duration.ofSeconds(DB_STARTUP_TIMEOUT))
                  .until(() -> postgreSQLTestContainer.isRunning());
        registry.add("spring.datasource.url", postgreSQLTestContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLTestContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLTestContainer::getUsername);
    }

    @Before
    public void init() {
        testDbUtils.cleanDB();
    }

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

}
