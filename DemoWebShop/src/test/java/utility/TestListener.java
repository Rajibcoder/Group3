package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

import org.apache.log4j.Logger;

public class TestListener implements ITestListener {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Log.class.getName());
	
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        // Generate a timestamp for the screenshot filename
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        // Define the screenshot file path
        String screenshotPath = "E:\\Screenshots\\"; // Specify your desired directory
        String screenshotName = result.getName() + "_" + timeStamp + ".png";

        // Capture and save the screenshot
        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File(screenshotPath + screenshotName);

            try {
                Files.copy(screenshotFile.toPath(), targetFile.toPath());
                System.out.println("Screenshot saved to: " + targetFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
}
