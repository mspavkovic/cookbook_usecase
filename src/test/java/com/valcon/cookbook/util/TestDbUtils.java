package com.valcon.cookbook.util;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TestDbUtils {

    @Autowired
    public Flyway flyway;

    public void cleanDB() {
        flyway.clean();
        flyway.migrate();
    }
}
