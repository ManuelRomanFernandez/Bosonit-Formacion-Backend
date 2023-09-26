package com.formacion.bosonit.block19selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NgListener.class)
public class NgExampleTests {
    private WebDriver driver;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    @Test
    public void openGoogleNgTest() throws InterruptedException {
        driver.get("https://google.com");

        driver.findElement(By.id("L2AGLb")).click();
        driver.findElement(By.tagName("textarea")).sendKeys("Selenium");
        driver.findElement(By.className("gNO89b")).submit();
        driver.findElement(By.tagName("textarea")).clear();

        Thread.sleep(1500);

        driver.findElement(By.tagName("textarea")).sendKeys("youtube");
        driver.navigate().back();
    }

}
