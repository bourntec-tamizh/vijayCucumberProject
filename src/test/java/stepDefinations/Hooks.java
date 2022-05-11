package stepDefinations;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Hooks extends TestBase{

    @BeforeStep
    public void beforeStep(){
        System.out.println("Cucumber Before Step");
    }

    @Before
    public void InitiateExtentReport(Scenario scenario) {
        System.out.println("Cucumber Before");
        test = reports.createTest("Scenario: "+scenario.getName());
    }

    @After
    public void closeExtentReport(Scenario scenario){
        System.out.println("Cucumber After");
        if(scenario.getStatus().toString().equalsIgnoreCase("PASSED")){
            test.info("Test Case completed")   ;
        }else{
            test.info("Test Case Completed")   ;
        }
        reports.flush();
    }

    @AfterStep
    public  void screenshotAfterScenario(Scenario scenario) {
        System.out.println("Cucumber After Step ");
//        if (scenario.isFailed()) {
//            if (driver != null) {
//                try {
//                    pause(2000);
//                    TakesScreenshot ts = (TakesScreenshot) driver;
//                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//                    scenario.attach(screenshot, "image/png", "screen1");
//                } catch (WebDriverException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}