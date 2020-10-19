package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	static ExtentReports report;
	static ExtentSparkReporter sparkReporter;
	static ExtentTest extentTest;
	static String sProjectPath=System.getProperty("user.dir");
	
	public ExtentReportManager() {
		sparkReporter=new ExtentSparkReporter(sProjectPath+"/Reports/");
		report=new ExtentReports();
		sparkReporter.config().setReportName("SWAGLABS Web Testing");
		sparkReporter.config().setDocumentTitle("SWAGLABS Automated Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setEncoding("utf-8");
		report.attachReporter(sparkReporter);
		return;
	}
	
	//Method to log the result by comparing actual and expected along with message
	public static void ValidateContent(String strActual, String strExpected, String Message){
		boolean boolIsSuccess = strActual.trim().toUpperCase().equals(strExpected.trim().toUpperCase());
		if (boolIsSuccess){
			extentTest.log(Status.PASS, Message+" PASS - Actual : "+strActual);
		}
		else{
			extentTest.log(Status.FAIL, Message+" FAIL - Actual: "+strActual+" Expected: "+strExpected);
		}
	}
	
	//Method to log the message using status
	public static void ValidateStatus(boolean sStatus, String Message){
		if (sStatus){
			extentTest.log(Status.PASS, Message+" : PASS");
		}
		else{
			extentTest.log(Status.FAIL, Message+" :FAIL");
		}
	}
	
	//Method to log information
	public static void logInfo(String Test, String log){
		if(Test.equals(""))
			extentTest.log(Status.INFO, log);
		else {
			extentTest=report.createTest(Test);
			extentTest.log(Status.INFO, log);
		}
	}	
	
	//Flush method
	public void flush() {
		report.flush();
	}
}
