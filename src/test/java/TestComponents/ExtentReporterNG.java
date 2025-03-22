package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getExtentReportObject(String currentDateandTime) {
		String reportsPath = System.getProperty("user.dir")+"//Reports//Report_"+currentDateandTime+"//TestReport"+".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportsPath);
		reporter.config().setDocumentTitle("Automation Practice");
		reporter.config().setReportName("TestNG Test Results");
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Browser", "Edge");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Owning Team", "QA Team");
		return report;
		
	}

}
