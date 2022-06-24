package com.vtiger.genericLib;

import java.io.File;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListners implements ITestListener {

	ExtentReports report;
	ExtentTest etest;
	
	public void onTestStart(ITestResult result) {
		
		etest=report.createTest( result.getMethod().getMethodName());
		
		Reporter.log(result.getName()+" method is satrted ",true);
	
	}

	public void onTestSuccess(ITestResult result) {
		etest.log(Status.PASS,result.getMethod().getMethodName());
		etest.log(Status.PASS,result.getThrowable());
	
		
		
		Reporter.log(result.getName()+" method is passed ",true);
		
	}

	public void onTestFailure(ITestResult result) {

		etest.log(Status.FAIL,result.getMethod().getMethodName());
		etest.log(Status.FAIL, result.getThrowable());
		
		try {
			String screenshot = WebDriverCommonLibraries.takeScreenShot(BaseClass.sdriver, result.getMethod().getMethodName());
			etest.addScreenCaptureFromPath(screenshot);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//		// TODO Auto-generated method stub
//		String testname=result.getMethod().getMethodName();
//		Reporter.log("=====Executing====",true);
//		EventFiringWebDriver edriver=new EventFiringWebDriver(BaseClass.sdriver);
//		File src=edriver.getScreenshotAs(OutputType.FILE);
//		try
//		{
//			FileUtils.copyFile(src, new File("./screenshots/"+testname+".png"));
//			
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
	}

	public void onTestSkipped(ITestResult result) {
		
		Reporter.log(result.getName()+" method is skipped",true);
		
	}

	public void onStart(ITestContext context) {

		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReport/report.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Framework extent report");
		spark.config().setDocumentTitle("Nagesh's document");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("created by ","Nagesh");
		report.setSystemInfo("Review by ", "Sanjay");
		report.setSystemInfo("platform", "window");
		
		Reporter.log(context.getName()+"  test is startes",true);
	
	}

	public void onFinish(ITestContext context) {
		report.flush();
		Reporter.log(context.getName()+" test is finished",true);
	}
	
	

}
