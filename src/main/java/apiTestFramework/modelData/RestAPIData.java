package apiTestFramework.modelData;

import io.restassured.http.Method;

public class RestAPIData {

    private Method method;
    private String baseURI;
    private int statusCode;
    private String statusLine;
    private String responseBody;
    private String contentType;
    private String successCode;

    public RestAPIData setMethod(Method method){
        this.method = method;
        return this;
    }

    public RestAPIData setBaseURI(String baseURI){
        this.baseURI = baseURI;
        return this;
    }

    public RestAPIData setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public RestAPIData setStatusLine(String statusLine) {
        this.statusLine = statusLine;
        return this;
    }

    public RestAPIData setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    public RestAPIData setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public RestAPIData setSuccessCode(String successCode){
        this.successCode = successCode;
        return this;
    }

    public Method getMethod() { return method; }

    public String getBaseURI() { return baseURI; }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getContentType() {
        return contentType;
    }

    public String getSuccessCode() {
        return successCode;
    }
}
