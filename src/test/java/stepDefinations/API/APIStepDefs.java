package stepDefinations.API;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import libraries.APIEndPoints;
import libraries.ConfigReader;
import stepDefinations.TestBase;

import java.io.IOException;
import java.net.ResponseCache;

import static io.restassured.RestAssured.given;

public class APIStepDefs extends TestBase {


    @When("I submit GET request for usersAPI")
    public void iSubmitGETRequestForUsersAPI() throws IOException {
        RestAssured.baseURI = ConfigReader.getConfigValue("URL_API");

        String apiEndPoint = APIEndPoints.UsersAPI.getResource()+"/2";
        logger.info("API End Point: "+apiEndPoint);

        Response response = given().log().all().when().get(apiEndPoint).then().log().all().extract().response();

//        logger.info("Response2: "+response.asString());
//        logger.info("Response3: "+response.prettyPrint());

    }

    @Then("I verify status code is {string}")
    public void iVerifyStatusCodeIs(String arg0) {

    }
}
