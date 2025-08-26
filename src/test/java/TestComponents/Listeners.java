package TestComponents;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.ExtentReport;

public class Listeners extends Basetestng implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReport.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>() ;
	
	
	public void onTestStart(ITestResult result) {
		// her we need title of the test method so we use getMethodName
		
		test = extent.createTest(result.getMethod().getMethodName());
		
         extentTest.set(test);//unique thread id(ErrorValidationTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.get().log(Status.PASS, "Test Passed");
		
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
	    extentTest.get().fail(result.getThrowable());

	    WebDriver driver = null;
	    Object testInstance = result.getInstance();

	    // Get driver from Basetestng
	    if (testInstance instanceof Basetestng) {
	        driver = ((Basetestng) testInstance).getDriver();
	    }

	    String filepath = null;
	    try {
	        filepath = getscreenShot(result.getMethod().getMethodName(), driver);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    if (filepath != null) {
	        extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	    }
	}


	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}
	
	
	

}
