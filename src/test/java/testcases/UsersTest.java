package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import routes.Routes;

public class UsersTest extends Base {

@Test
void getAllUsers(){

    given()
        .contentType(ContentType.JSON)
    .when()
        .get(Routes.GET_ALL_USERS)
    .then()
        .statusCode(200);

}



}
