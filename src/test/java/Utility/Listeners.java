package Utility;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void onStart(ITestContext testContext) {
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtReports/myReport.html");
		
		htmlReporter.config().setDocumentTitle("API Automation Report");
		htmlReporter.config().setReportName("Rest API Testing Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Ajay");
	}
	
	
//	public void onTestStart(ITestResult tr) {
//		System.out.println("Test Started --");
//	}
	
	
	public void onTestSuccess(ITestResult result) {
//		System.out.println("Test Passed successfully --");
		
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case Passed is :-- " + result.getName());
	}
	
	
	public void onTestFailure(ITestResult result) {
//		System.out.println("Test Failed --");
		
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case Failed is :-- " + result.getName());
		test.log(Status.FAIL, "Error shown is :-- " + result.getThrowable());
	}
	
	
	public void onTestSkipped(ITestResult result) {
//		System.out.println("Test Skipped --");
		
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case Skipped is :-- " + result.getName());
	}
	
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
	}

}

