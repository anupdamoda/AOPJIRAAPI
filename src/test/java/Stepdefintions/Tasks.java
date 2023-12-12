package Stepdefintions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Tasks {
    public RequestSpecification httpRequest;
    public static Response response;
    public int ResponseCode;
    public ResponseBody body;

    private static String requestBody = "{\"transition\":{\"id\":\"31\"}}";

    @Given("I hit the url of jira api endpoint")
    public void i_hit_the_url_of_jira_api_endpoint() {
        RestAssured.baseURI = "https://aceautomationacademyjira.atlassian.net/";
    }

    @When("I pass the url of the task api endpoint of the Issue {}")
    public void iPassTheUrlOfTheTaskApiEndpointOfTheIssue(String arg0) {
        httpRequest = given()
                .header("Authorization", "Basic cGh1b25nbGV2aWV0bmFtOThAZ21haWwuY29tOkFUQVRUM3hGZkdGMGJsRFdtT2E3bzEyZWxUajBWZXlFOVhQOU90RkFvS0pPcUJ4ZUprZXVyZUFsWWM5eFVrbE9yQlRIN2FQZkdXdUE5dVpQTmVPSW0za1VHbE1Ub3VVM2RWVU9lb0lwZlBGNDFYcV9GZF9XNGlFSGZMUjNwV2o1MXBGRmYycDBXRUVSWnhIemozMHczX01WYUhvV0xSYjFYNXRrVmtJTnlDTExYY1lReHlpNXJZQT1ENDI5NzVDQg==");
        response = httpRequest.get("rest/api/3/issue/" + arg0);
    }

    @When("I pass the url of the post task api endpoint of the Issue {} for updating status")
    public void iPassTheUrlOfTheTaskApiEndpointOfTheIssueForUpdatingStatus(String arg0) {
         response = given()
                .header("Authorization","Basic cGh1b25nbGV2aWV0bmFtOThAZ21haWwuY29tOkFUQVRUM3hGZkdGMGJsRFdtT2E3bzEyZWxUajBWZXlFOVhQOU90RkFvS0pPcUJ4ZUprZXVyZUFsWWM5eFVrbE9yQlRIN2FQZkdXdUE5dVpQTmVPSW0za1VHbE1Ub3VVM2RWVU9lb0lwZlBGNDFYcV9GZF9XNGlFSGZMUjNwV2o1MXBGRmYycDBXRUVSWnhIemozMHczX01WYUhvV0xSYjFYNXRrVmtJTnlDTExYY1lReHlpNXJZQT1ENDI5NzVDQg==")
                .header("Content-Type","application/json")
                .and()
                .body(requestBody)
                .when()
                .post("rest/api/3/issue/" + arg0 +"/transitions")
                .then()
                .extract().response();
    }

    @Then("^I receive the response code as 200$")
    public void I_receive_the_response_code_as_200() {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, 200);
    }

    @Then("^I receive the response code as 204$")
    public void I_receive_the_response_code_as_204() {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, 204);
    }

    @Then("I receive the response body IssueType as {}")
    public void iReceiveTheResponseBodyIssueTypeAs(String arg0) {
        body = response.getBody();
        //convert response body to string
        String responseBody = body.asString();
        //JSON Representation from Response Body
        JsonPath jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("fields.issuetype.name").toString();
        assertEquals(arg0, s);
    }

    @Then("I receive the response body of Issue Status as {}")
    public void iReceiveTheResponseBodyIssueStatusAs(String arg0) {
        body = response.getBody();
        //convert response body to string
        String responseBody = body.asString();
        //JSON Representation from Response Body
        JsonPath jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("fields.status.name").toString();
        assertEquals(arg0, s);
    }
    @Then("I receive the response body assignee as {}")
    public void iReceiveTheResponseBodyAssignee(String arg0) {
        body = response.getBody();
        //convert response body to string
        String responseBody = body.asString();
        //JSON Representation from Response Body
        JsonPath jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("fields.assignee.displayName").toString();
        assertEquals(arg0, s);
    }


}
