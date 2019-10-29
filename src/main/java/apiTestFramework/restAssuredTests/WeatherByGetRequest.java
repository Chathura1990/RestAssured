package apiTestFramework.restAssuredTests;

import apiTestFramework.modelData.UserData;
import io.restassured.http.Method;
import apiTestFramework.modelData.RestAPIData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import apiTestFramework.restMethodHelper.RestAPIMethodService;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class WeatherByGetRequest {

    private RestAPIMethodService APIService = new RestAPIMethodService();

    @DataProvider
    private Object[] getTestDataFromJsonFile() throws IOException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./src/main/resources/testData.json"));
        JSONObject jsonObject = (JSONObject) obj;
        return new UserData[]{new UserData()
                .setEmail(String.valueOf(jsonObject.get("email")))
                .setName(String.valueOf(jsonObject.get("name")))
                .setPassword(String.valueOf(jsonObject.get("password")))};
    }

    @Test(priority = 1)
    public void getWeatherDetailsByChainMethod(){
        given()
                .when()
                .get("http://restapi.demoqa.com/utilities/weather/city/Colombo")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .assertThat().body("City", equalTo("Colombo"))
                .header("Content-Type","application/json");
    }

    @Test(priority = 2, dataProvider = "getTestDataFromJsonFile")
    public void userDetailsValidationThroughGET(UserData userData){
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", userData.getEmail());
        RestAPIData restAPIData = APIService.getApiDetailsAndReturnResults(new RestAPIData()
                .setBaseURI("http://users.bugred.ru/tasks/rest")
                .setMethod(Method.GET),requestParams,"getuser");
        Assert.assertEquals(restAPIData.getStatusCode(),200);
        Assert.assertEquals(restAPIData.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(priority = 3, dataProvider = "getTestDataFromJsonFile")
    public void userDetailsValidationThroughPOST(UserData userData){
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", userData.getEmail());
        requestParams.put("name", userData.getName());
        requestParams.put("password", userData.getPassword());
        RestAPIData restAPIData = APIService.postApiDetailsAndReturnResults(new RestAPIData()
                        .setBaseURI("http://users.bugred.ru/tasks/rest")
                        .setMethod(Method.POST)
                        .setContentType("application/json"),requestParams,"doregister");
        Assert.assertEquals(restAPIData.getStatusCode(),200);
//        Assert.assertEquals(restAPIData.getSuccessCode(), "OPERATION_SUCCESS");
    }

    @Test(priority = 4, dataProvider = "getTestDataFromJsonFile")
    public void customerDetailsDeletionThroughDELETE(UserData userData){
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", userData.getEmail());
        APIService.deleteApiDetailsAndReturnResults(new RestAPIData()
                        .setBaseURI("http://users.bugred.ru/tasks/rest")
                        .setMethod(Method.DELETE)
                        .setContentType("application/json")
                ,requestParams, "deleteuser");
    }
}
