package PageObjects;

import libraries.Validations;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinations.TestBase;
import testData.TestDataGenerator;

import java.io.IOException;
import java.util.List;

public class LoginPage extends TestBase {


    public LoginPage() {
        InitElements();
    }

    private void InitElements() {

    }

    public void openBrowser() throws IOException {
        try {
            logger.info("Browser selected#" + browser);
            logger.info("Operating System#" + System.getProperty("os.name"));
            if (browser.equalsIgnoreCase("chrome")) {
                LaunchChromeBrowser();
            } else if (browser.equalsIgnoreCase("edge")) {
                LaunchEdge();
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            logger.error("Browser failed to opened");
        }
    }


    public void navigateToApplicationPage() throws IOException {
        navigatetoURL();
    }


}
