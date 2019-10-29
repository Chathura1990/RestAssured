package apiTestFramework.modelData;

import io.restassured.http.Method;

public class UserData {

    private String email;
    private String name;
    private String password;

    public UserData setEmail(String email){
        this.email = email;
        return this;
    }

    public UserData setName(String name){
        this.name = name;
        return this;
    }

    public UserData setPassword(String password){
        this.password = password;
        return this;
    }

    public String getEmail() { return email; }

    public String getName() { return name; }

    public String getPassword() { return password; }
}
