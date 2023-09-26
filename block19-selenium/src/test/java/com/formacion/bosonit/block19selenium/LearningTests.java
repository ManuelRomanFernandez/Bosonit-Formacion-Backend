package com.formacion.bosonit.block19selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class LearningTests {
    private WebDriver driver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void openGoogleTest() throws InterruptedException{
        driver.get("https://google.com");

        driver.findElement(By.id("L2AGLb")).click();
        driver.findElement(By.tagName("textarea")).sendKeys("Selenium");
        driver.findElement(By.className("gNO89b")).submit();
        driver.findElement(By.tagName("textarea")).clear();

        Thread.sleep(1500);

        driver.findElement(By.tagName("textarea")).sendKeys("youtube");
        driver.navigate().back();
    }

    @Test
    void windowManagementTest() throws InterruptedException{
        driver.get("https://google.com");

        driver.manage().window().minimize();
        Thread.sleep(1500);

        driver.manage().window().fullscreen();
        Thread.sleep(1500);

        driver.manage().window().maximize();
        Thread.sleep(1500);

        System.out.println("Initial size" + driver.manage().window().getSize());
        System.out.println("Initial position" + driver.manage().window().getPosition());

        driver.manage().window().setSize(new Dimension(300, 300));
        Thread.sleep(1500);

        driver.manage().window().setPosition(new Point(500, 500));
        Thread.sleep(1500);
    }

    @Test
    void locatorsTest() throws InterruptedException{
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.name("user-name")).sendKeys("Locator by name");
        Thread.sleep(2000);
        driver.findElement(By.name("user-name")).clear();

        driver.findElement(By.id("user-name")).sendKeys("Locator by id");
        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).clear();

        driver.findElement(By.cssSelector("#user-name")).sendKeys("Locator by css selector");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#user-name")).clear();

        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("Locator by xpath");
        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).clear();
    }

    @Test
    void implicitWaitTest(){
        driver.get("https://www.saucedemo.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        assertThrows(NoSuchElementException.class,
                () -> driver.findElement(By.tagName("ThrowException")).click());
    }

    @Test
    void explicitWaitTest() {
        driver.get("https://www.saucedemo.com/");

        WebElement element = driver.findElement(By.id("login-button"));

        assertThrows(TimeoutException.class,
                () -> clickOnWebElement(driver, element, 5));
    }

    private static void clickOnWebElement(WebDriver driver, WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.titleIs("Exception"));

        element.click();
    }

    @Test
    void alertTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,350)", "");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-1']")).click();

        driver.findElement(By.xpath("//*[@id=\"alertButton\"]")).click();

        Thread.sleep(2000);

        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
        System.out.println(driver.switchTo().alert().getText());
        Thread.sleep(2000);
        driver.switchTo().alert().dismiss();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
        System.out.println(driver.switchTo().alert().getText());
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        Thread.sleep(5000);
    }

    @Test
    void popupTest() throws InterruptedException, AWTException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,350)", "");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-0']")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[normalize-space()='New Window Message'])[1]")).click();

        Robot robot = new Robot();
        robot.mouseMove(500, 10);
        Thread.sleep(2000);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(5000);
    }

    @Test
    void listenersTest() {
        WebDriverListener listener = new MyListener();
        WebDriver webDriver = new EventFiringDecorator<>(listener).decorate(driver);

        webDriver.get("https://www.saucedemo.com/");

        webDriver.findElement(By.name("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.name(("password"))).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }

    @Test
    void actionClassTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions builder = new Actions(driver);
        driver.get("https://demoqa.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        builder.moveToElement(driver.findElement(By.xpath("//div[@id='app']//div[5]"))).click();

        Thread.sleep(5000);
    }
}
