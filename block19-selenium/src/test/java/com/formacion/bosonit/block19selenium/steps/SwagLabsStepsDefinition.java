package com.formacion.bosonit.block19selenium.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabsStepsDefinition {
    private WebDriver driver;
    private String itemName;

    // Background: Access as standard-user

    @Given("^Navigate to SwagLabs homepage$")
    public void navigate_to_swagLabs() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @And("^Insert username$")
    public void insert_username(DataTable data) {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(data.asLists().get(1).get(0));
    }

    @And("^Insert password$")
    public void insert_password(DataTable data) {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(data.asLists().get(1).get(0));
    }

    @And("^Click login$")
    public void click_login() {
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    // Scenario: Verify login

    @Then("^Verify url$")
    public void verify_url() {
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @And("^Close SwagLabs window$")
    public void close_swagLabs_windows() {
        driver.close();
        driver.quit();
    }

    // Scenario: Check first item and add it to shopping cart

    @When("^Inspect first item$")
    public void inspect_first_item() {
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/a[1]/div[1]")).click();
    }

    @And("^Add item to shopping cart$")
    public void add_item() {
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/button[1]")).click();
        itemName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]")).getText();
    }

    @And("^Navigate to shopping cart$")
    public void navigate_shopping_cart() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @Then("^Check item name$")
    public void check_item_name() {
        Assert.assertEquals("Sauce Labs Backpack", itemName);
    }
}
