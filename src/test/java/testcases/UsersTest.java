package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Users;
import routes.Routes;

public class UsersTest extends Base {

    @Test(priority = 0)
    void getAllUsers() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.GET_ALL_USERS)
                .then()
                .statusCode(200);

    }

    @Test(priority = 1)
    void getUsersById() {

        given()
                .pathParam("id", configReader.getProperty("userId"))
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.GET_USER_BY_ID)
                .then()
                .statusCode(200);

    }

    @Test(priority = 2)
    void getUsersWithLimit() {

        given()
                .pathParam("limit", "3")
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.GET_USERS_WITH_LIMIT)
                .then()
                .statusCode(200)
                .body("size()", equalTo(3));
    }

    @Test(priority = 3)
    void getUsersInDescendingOrder() {

        Response response = given()
                .pathParam("order", "desc")
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.GET_USERS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> userIds = response.jsonPath().getList("id",Integer.class);

        Assert.assertTrue(isSortedDescending(userIds));
    }

    @Test(priority = 4)
    void getUsersInAscendingOrder() {

        Response response = given()
                .pathParam("order", "asc")
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.GET_USERS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> userIds = response.jsonPath().getList("id",Integer.class);

        Assert.assertTrue(isSortedAscending(userIds));
    }

    @Test
    void testCreateUser(){

        Users users = Payload.usersPayload();

        given()
            .contentType(ContentType.JSON)
            .body(users)
        .when()
            .post(Routes.CREATE_USER)
        .then()
            .log().body();
    }

    @Test
    void testUpdateUser(){

        Users users = Payload.usersPayload();

        given()
            .pathParam("id", configReader.getProperty("userId"))
            .contentType(ContentType.JSON)
            .body(users)
        .when()
            .put(Routes.UPDATE_USER)
        .then()
            .body("username", is(users.getUsername()))
            .log().body();
    }

    @Test
    void testDeleteUser(){

        given()
            .pathParam("id", configReader.getProperty("userId"))
            .contentType(ContentType.JSON)
        .when()
            .delete(Routes.DELETE_USER)
        .then()
            .log().body();
    }
}
