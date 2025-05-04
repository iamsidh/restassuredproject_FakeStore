package testcases;

import static io.restassured.RestAssured.given;

import routes.Routes;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import payloads.Payload;
import pojo.Login;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoginTestcases extends Base{

    @Test
    void testLoginWithValidCreds(){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username", configReader.getProperty("username"));
        jsonObject.put("password",configReader.getProperty("password") );

        given()
            .contentType(ContentType.JSON)
            .body(jsonObject.toString())
        .when()
            .post(Routes.LOGIN)
        .then()
            .assertThat().body(containsString("token"))
            .log().body();
    }

    @Test
    void testLoginWithInValidCreds(){

        Login loginData = Payload.loginPayload();

        given()
            .contentType(ContentType.JSON)
            .body(loginData)
        .when()
            .post(Routes.LOGIN)
        .then()
            .assertThat().body(containsString("incorrect"))
            .log().body();
    }

}
