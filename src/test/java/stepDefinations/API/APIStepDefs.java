package stepDefinations.API;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import libraries.APIEndPoints;
import org.testng.Assert;
import responsePojos.userListResponse.Datum;
import responsePojos.userListResponse.UserListResponse;
import responsePojos.usersResponse.UsersResponse;
import stepDefinations.TestBase;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class APIStepDefs extends TestBase {

    private Response response;




    @When("I submit GET request for usersAPI")
    public void iSubmitGETRequestForUsersAPI() throws IOException {

        String apiEndPoint = APIEndPoints.UsersAPI.getResource()+"/2";
        logger.info("API End Point: "+apiEndPoint);


        Response response = given().log().all()
                .spec(requestSpecification())
//                .param("page", 2)
                .when().get(apiEndPoint)
                .then().log().all().extract().response();

        logger.info("Status Code: "+response.statusCode());

        UsersResponse resp = response.as(UsersResponse.class);
        logger.info("First Name: "+resp.getData().getFirst_name());
        logger.info("Email: "+resp.getData().getEmail());

        logger.info("text: "+resp.getSupport().getText());
        logger.info("url: "+resp.getSupport().getUrl());
    }

    @Then("I verify status code is {string}")
    public void iVerifyStatusCodeIs(String expStatusCode) {
        String actStatusCode = ""+ response.getStatusCode();
        if(actStatusCode.equalsIgnoreCase(expStatusCode)) {
            test.pass("Status Code is: " + actStatusCode);
        }
        else
        {
            test.fail("Status Code is: " + actStatusCode +" instead of: "+expStatusCode);
            Assert.fail("Status Code is: " + actStatusCode +" instead of: "+expStatusCode);
        }
    }

    @When("I submit GET request for usersAPI for user {string}")
    public void iSubmitGETRequestForUsersAPIForUser(String userId) {
        String apiEndPoint = APIEndPoints.UsersAPI.getResource()+"/"+userId;
        logger.info("API End Point: "+apiEndPoint);

        response = given().log().all()
                .spec(requestSpecification())
                .when().get(apiEndPoint)
                .then().log().all().extract().response();

        logger.info("Status Code: "+response.statusCode());

    }

    @And("User verify response values")
    public void userVerifyResponseValues() {
        UsersResponse resp = response.as(UsersResponse.class);
        logger.info("First Name: "+resp.getData().getFirst_name());
        logger.info("Email: "+resp.getData().getEmail());

        logger.info("text: "+resp.getSupport().getText());
        logger.info("url: "+resp.getSupport().getUrl());
    }

    @And("User verify response values for user list")
    public void userVerifyResponseValuesUserList() {
        UserListResponse resp = response.as(UserListResponse.class);
        ArrayList<Datum> dataVal = resp.getData();
        for(int i=0;i<dataVal.size();i++){
          logger.info( dataVal.get(i).getEmail());
        }
    }

    @When("I submit GET request for usersAPI for list of users in page {string}")
    public void iSubmitGETRequestForUsersAPIForListOfUsersInPage(String page) {
        String apiEndPoint = APIEndPoints.UsersAPI.getResource();
        logger.info("API End Point: "+apiEndPoint);

        response = given().log().all()
                .spec(requestSpecification())
                .param("page", page)
                .when().get(apiEndPoint)
                .then().log().all().extract().response();

        logger.info("Status Code: "+response.statusCode());
    }
}
