package stepDefinations;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.Test;

import java.io.File;

public class ExtentReportDemo {

    @Test
    public void testExtent(){
        ExtentReports reports = new ExtentReports();
        ExtentHtmlReporter htmlReporter;
        ExtentTest test;

        String reportName = System.getProperty("user.dir") + "/Reports/ExtentReportBourntec.html";
        htmlReporter = new ExtentHtmlReporter(new File(reportName));
        htmlReporter.loadXMLConfig(String.valueOf(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml")));

        reports.setSystemInfo("Browser",   "Chrome");
        reports.setSystemInfo("Author", "Bourntec");
        reports.setSystemInfo("Executed By", System.getProperty("user.name"));
        reports.setSystemInfo("Operating System", System.getProperty("os.name"));

        reports.attachReporter( htmlReporter);

        test = reports.createTest("Test Extent Report 1");
        test.info("This is info message");
        test.pass("Test Case is passed");

        test = reports.createTest("Test Extent Report 2");
        test.info("This is info message");
        test.pass("Test Case is passed");
        test.fail("Test case is failed");

        reports.flush();
    }

}
