package com.valcon.cookbook.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.slf4j.Slf4j;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/")
@Slf4j
public class CucumberTest {

}
