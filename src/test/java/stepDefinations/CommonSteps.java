package stepDefinations;

import io.cucumber.java.en.And;

import javax.mail.Message;

public class CommonSteps extends TestBase {
    Message[] messages;


    @And("pause {string}")
    public void pause(String time) throws InterruptedException {
        Thread.sleep(Integer.valueOf(time));
    }
}