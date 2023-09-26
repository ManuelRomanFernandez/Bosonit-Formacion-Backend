package com.formacion.bosonit.block19selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Arrays;

public class MyListener implements WebDriverListener {
    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        System.out.println("afterClick event was fired. Element: " + element.toString());
        System.out.println("----------------------------");
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        System.out.println("beforeFindElement was fired. Locator: " + locator.toString());
        System.out.println("----------------------------");
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        System.out.println("afterFindElement was fired. Locator: " + locator.toString());
        System.out.println("----------------------------");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.beforeSendKeys(element, keysToSend);
        System.out.println("beforeSendKeys was fired. Text: " + Arrays.toString(keysToSend));
        System.out.println("----------------------------");
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
        System.out.println("afterSendKeys was fired. Text: " + Arrays.toString(keysToSend));
        System.out.println("----------------------------");
    }
}
