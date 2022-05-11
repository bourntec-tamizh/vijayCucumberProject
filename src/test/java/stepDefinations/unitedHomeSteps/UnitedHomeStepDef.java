package stepDefinations.unitedHomeSteps;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class UnitedHomeStepDef {
    LoginPage loginPage = new LoginPage();
    HomePage homePage =  new HomePage();

    @When("^User open united home page$")
    public void user_open_united_home_page() throws Throwable {
        loginPage.openBrowser();
        loginPage.navigateToApplicationPage();
    }

    @And("User selects Book tab")
    public void userSelectsBookTab() throws Exception {
        homePage.selectBookmenu();
    }

    @And("^User selects flight type as \"([^\"]*)\"$")
    public void user_selects_flight_type_as_something(String flightType) throws Throwable {
        homePage.selectFlightType(flightType);
    }
}
