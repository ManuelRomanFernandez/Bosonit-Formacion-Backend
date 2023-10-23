package com.formacion.bosonit.block19selenium.runners;

import com.formacion.bosonit.block19selenium.listeners.NgListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = {
                "src/test/java/com/formacion/bosonit/block19selenium/features/googleNg.feature"},
        glue = "com.formacion.bosonit.block19selenium.steps"
)
@Listeners(NgListener.class)
public class NgTestRunner extends AbstractTestNGCucumberTests {
}
