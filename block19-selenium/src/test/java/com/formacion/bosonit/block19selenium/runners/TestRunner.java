package com.formacion.bosonit.block19selenium.runners;

import com.formacion.bosonit.block19selenium.listeners.NgListener;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Listeners;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/java/com/formacion/bosonit/block19selenium/features/swagLabs.feature",
                "src/test/java/com/formacion/bosonit/block19selenium/features/google.feature",
                "src/test/java/com/formacion/bosonit/block19selenium/features/demoqa.feature"},
        glue = "com.formacion.bosonit.block19selenium.steps"
)
@Listeners(NgListener.class)
public class TestRunner { }