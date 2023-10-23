package com.formacion.bosonit.block19selenium.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleStepsDefinition {
    private WebDriver driver;

    @Given("Navigate to Google homepage")
    public void navigate_to_google_homepage() {
        driver = new ChromeDriver();
        driver.get("https://google.com");
    }

    @And("Close configuration window")
    public void close_configuration_window() {
        driver.findElement(By.id("L2AGLb")).click();
    }

    @When("Introduce text to search input")
    public void introduce_text_to_search_input() {
        driver.findElement(By.tagName("textarea")).sendKeys("selenium");
        driver.findElement(By.className("gNO89b")).submit();
        driver.findElement(By.tagName("textarea")).clear();
    }

    @Then("Check web title starts with text")
    public void check_web_title_starts_with_text() {
        Assert.assertTrue(driver.getTitle().startsWith("selenium"));
    }

    @And("Close Google window")
    public void close_google_window() {
        driver.close();
        driver.quit();
    }
}
