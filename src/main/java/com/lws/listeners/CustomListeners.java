package com.lws.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.lws.base.Page;
import com.lws.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener {

    // This method is invoked when the test starts
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the ExtentReports with the test method name in uppercase
        test = reports.createTest(result.getMethod().getMethodName().toUpperCase());
    }

    // This method is invoked when the test successfully finishes
    public void onTestSuccess(ITestResult result) {
        // Log the successful test result in the ExtentReports
        test.log(Status.PASS, result.getName().toUpperCase() + " Pass");
        // Flush the ExtentReports to ensure all information is written to the report
        reports.flush();
    }

    // This method is invoked when the test fails
    public void onTestFailure(ITestResult result) {
        try {
        	Thread.sleep(1000);
            // Capture a screenshot on test failure
            Utilities.CaptureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Log the screenshot link and image in the TestNG report
        Reporter.log("<a target=\"_blank\" href=\"" + Utilities.screenshotPath + "\">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=\"" + Utilities.screenshotPath + "\"><img src=\"" + Utilities.screenshotPath + "\" height=200 width=200></img></a>");
        
        // Log the failure message and exception in the ExtentReports
        test.log(Status.FAIL, result.getName().toUpperCase() + " Failed with exception: " + result.getThrowable());
        // Attach the screenshot to the ExtentReports
		test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotPath).build());
        
        // Flush the ExtentReports to ensure all information is written to the report
        reports.flush();
    }

    // This method is invoked when the test is skipped
    public void onTestSkipped(ITestResult result) {
    	 test.log(Status.SKIP, result.getName().toUpperCase() + "Skip");
         // Flush the ExtentReports to ensure all information is written to the report
         reports.flush();
    }

    // This method is invoked when the test fails but is within success percentage
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        // This can be implemented to handle partial successes if required
    }

    // This method is invoked when the test context starts
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        // This can be implemented to handle any setup before the test suite starts
    }

    // This method is invoked when the test context finishes
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        // This can be implemented to handle any cleanup after the test suite finishes
    }
}
