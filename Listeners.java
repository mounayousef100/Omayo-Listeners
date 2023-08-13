package com.omayo.utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.omayo.base.Base;

public class Listeners extends Base implements ITestListener {
    private ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
    ExtentReports extentReport = ExtendReport.getExtentReport();

    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTestThread.set(extentReport.createTest(testName));
    }

    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTestThread.get().log(Status.PASS, testName + " Test Passed");
    }

    public void onTestFailure(ITestResult result) {
WebDriver driver = null;
		
		String testMethodName = result.getName();
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			takeScreenshot(testMethodName,driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		extentReport.flush();

	}

}