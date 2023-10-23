package com.formacion.bosonit.block19selenium.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DemoQAStepsDefinition {
    private WebDriver driver;
    private JavascriptExecutor js;

    private String selectorValue = "";

    @Given("Navigate to DemoQA homepage")
    public void navigate_to_demo_qa_homepage() {
        driver = new ChromeDriver();

        driver.get("https://demoqa.com/");

        js = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        driver.manage().window().maximize();
    }

    @When("Access to widgets section")
    public void access_to_widgets_section() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[4]")).click();
    }

    @And("Click on Select Menu")
    public void click_on_select_menu() {
        js.executeScript("window.scrollBy(0,600)", "");
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/ul[1]/li[9]")).click();
    }

    @And("Select first box third option")
    public void select_first_box() throws InterruptedException {
        WebElement selector = driver.findElement(By.cssSelector("body > " +
                        "div:nth-child(6) > " +
                        "div:nth-child(2) > " +
                        "div:nth-child(1) > " +
                        "div:nth-child(2) > " +
                        "div:nth-child(2) > " +
                        "div:nth-child(2) > " +
                        "div:nth-child(2) > " +
                        "div:nth-child(1) > " +
                        "div:nth-child(1) > " +
                        "div:nth-child(1) > " +
                        "div:nth-child(1)"));

        selector.click();

        driver.switchTo().activeElement().sendKeys(Keys.DOWN);
        driver.switchTo().activeElement().sendKeys(Keys.DOWN);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        selectorValue = selector.getText();
    }

    @Then("Check option value")
    public void check_option_value() {
        Assert.assertEquals("Group 2, option 1", selectorValue);
    }

    @And("Close TootsQA window")
    public void close_toots_qa_window() {
        driver.close();
        driver.quit();
    }
}
