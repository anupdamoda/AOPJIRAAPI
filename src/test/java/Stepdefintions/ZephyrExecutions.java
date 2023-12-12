package Stepdefintions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ZephyrExecutions {
    public static Response response;
    private static String requestBody;
    public int ResponseCode;


    @Given("I hit the url of zephyr api endpoint")
    public void iHitTheUrlOfZephyrApiEndpoint() {
        RestAssured.baseURI = "https://prod-api.zephyr4jiracloud.com/v2";
    }


    @When("I pass the url of the post task api endpoint of {}, {} for updating status as {}")
    public void iPassTheUrlOfThePostTaskApiEndpointOfForUpdatingStatusAs(String arg0, String arg1, String arg2) {
        requestBody = "{\"projectKey\":\"AOP\",\"testCaseKey\":\"" + arg0  + "\",\"testCycleKey\":\"" + arg1 + "\" ,\"statusName\":\"" + arg2 + "\"}";
        response = given()
                .header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2FjZWF1dG9tYXRpb25hY2FkZW15amlyYS5hdGxhc3NpYW4ubmV0IiwidXNlciI6eyJhY2NvdW50SWQiOiI3MTIwMjA6NzM3MGRlNzEtZjRmNi00NzhjLTg2MDUtYjE2ZDQ2MjU4YzMyIn19LCJpc3MiOiJjb20udGhlZC56ZXBoeXIuamUiLCJzdWIiOiI1YjY0ZTdjOC0xYTc5LTM2OGQtYTUzOC0xMjRiY2FiYjhkNTEiLCJleHAiOjE3MzIwMDI5MDQsImlhdCI6MTcwMDQ2NjkwNH0.cYrX8XhaW7YsH1CtePUtQBZwRLOqBt1OddiwKXlruxk")
                .header("Content-Type","application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/testexecutions")
                .then()
                .extract().response();
    }


    @Then("I receive the response code as {int}")
    public void iReceiveTheResponseCodeAs(int arg0) {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, 201);
    }

}
