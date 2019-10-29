package apiTestFramework.restMethodHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import apiTestFramework.modelData.RestAPIData;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;

public class RestAPIMethodService {

    private RequestSpecification httpRequest;
    private Response response;

    /**
     * @param restAPIData baseURI, API Method
     * @param param URI end query path
     * @return statu code, status line, content type, response body
     */
    public RestAPIData getApiDetailsAndReturnResults(RestAPIData restAPIData,JSONObject requestParams, String param){
        //Specify base URI
        baseURI = restAPIData.getBaseURI();
        //Request object
        httpRequest = RestAssured.given().contentType("application/json;charset=utf-8");
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(restAPIData.getMethod(), "/" + param);
        restAPIDataForGetMethod(restAPIData);

        //print response in console window
        System.out.println("Status code is: " + restAPIDataForGetMethod(restAPIData).getStatusCode());
        System.out.println("Status line is: " + restAPIDataForGetMethod(restAPIData).getStatusLine());
        System.out.println("Content type: " + restAPIDataForGetMethod(restAPIData).getContentType());
        System.out.println("Response body is: " + restAPIDataForGetMethod(restAPIData).getResponseBody());
        return restAPIData;
    }

    /**
     * @param restAPIData baseURI, API Method, content type
     * @param requestParams parameters to send through the POST method
     * @param param URI end query path
     * @return statu code, status line, content type, response body
     */
    public RestAPIData postApiDetailsAndReturnResults(RestAPIData restAPIData,JSONObject requestParams, String param){
        //Specify base URI
        baseURI = restAPIData.getBaseURI();
        //Request object
        httpRequest = RestAssured.given();
        //Request payload sending along with post request
        httpRequest.header("Content-Type",restAPIData.getContentType());
        httpRequest.body(requestParams.toJSONString());

        //Response object
        response = httpRequest.request(restAPIData.getMethod(), "/" + param);
        restAPIDataForPOSTMethod(restAPIData);
        System.out.println(response.toString());

        //print response in console window
        System.out.println("Status code is: " + restAPIDataForPOSTMethod(restAPIData).getStatusCode());
        System.out.println("Status line is: " + restAPIDataForPOSTMethod(restAPIData).getStatusLine());
        System.out.println("Content type: " + restAPIDataForPOSTMethod(restAPIData).getContentType());
        System.out.println("Response body is: " + restAPIDataForPOSTMethod(restAPIData).getResponseBody());
        return restAPIData;
    }

    /**
     * @param restAPIData baseURI, API Method, content type
     * @param requestParams parameters to send through the POST method
     * @param param URI end query path
     */
    public void deleteApiDetailsAndReturnResults(RestAPIData restAPIData,JSONObject requestParams, String param){
        //Specify base URI
        baseURI = restAPIData.getBaseURI();
        httpRequest = RestAssured.given();
        httpRequest.body(requestParams.toJSONString());
//        httpRequest.header("Content-Type",restAPIData.getContentType());
        //Response object
        response = httpRequest.request(restAPIData.getMethod(), "/" + param);
        //Response object
        System.out.println(response.prettyPrint());
    }

    private RestAPIData restAPIDataForGetMethod(RestAPIData restAPIData) {
        //Collect API data
        restAPIData.setResponseBody(response.getBody().asString())
                .setStatusCode(response.getStatusCode())
                .setStatusLine(response.getStatusLine())
                .setContentType(response.getContentType());
        return restAPIData;
    }

    private RestAPIData restAPIDataForPOSTMethod(RestAPIData restAPIData) {
        //Collect API data
        restAPIData.setResponseBody(response.getBody().asString())
                .setStatusCode(response.getStatusCode())
                .setStatusLine(response.getStatusLine())
                .setContentType(response.getContentType());
        return restAPIData;
    }

}
