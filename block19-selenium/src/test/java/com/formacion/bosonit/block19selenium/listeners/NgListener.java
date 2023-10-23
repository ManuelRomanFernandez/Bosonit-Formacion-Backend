package com.formacion.bosonit.block19selenium.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class NgListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("-------------------------------------");
        System.out.println("Test case is starting from NgListener");
        System.out.println("-------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("-----------------");
        System.out.println("<--- SUCCESS --->");
        System.out.println("-----------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("-----------------");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        System.out.println("------------------");
        System.out.println("From onTestSkipped");
        System.out.println("------------------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        System.out.println("-------------------------------");
        System.out.println("<--- FAILED DUE TO TIMEOUT --->");
        System.out.println("-------------------------------");
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        System.out.println("---------------------");
        System.out.println("<---- STARTING ----->");
        System.out.println("---------------------");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("----------------------");
        System.out.println("<---- FINISHING ----->");
        System.out.println("----------------------");
    }
}
