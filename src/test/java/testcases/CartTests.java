package testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Cart;
import pojo.Users;
import routes.Routes;
public class CartTests extends Base{


    @Test
    void getAllCarts(){

        given()
        .when()
            .get(Routes.GET_ALL_CARTS)
        .then()
            .body("size()", greaterThan(0))
            .log().body();

    }

    @Test
    void getCartById(){

        given()
            .pathParam("id", configReader.getProperty("cartId"))
        .when()
            .get(Routes.GET_CART_BY_ID)
        .then()
            .statusCode(200)
            .body("id", equalTo(configReader.getIntProperty("cartId")))
            .log().body();

    }


    @Test
    void getCartByDates(){

     Response response =   given()
            .pathParam("startdate", configReader.getProperty("startdate"))
            .pathParam("enddate", configReader.getProperty("enddate"))
        .when()
            .get(Routes.GET_CARTS_BY_DATE_RANGE)
        .then()
            .statusCode(200)
            .log().body()
            .extract().response();

        List<String> cartDates = response.jsonPath().getList("date",String.class);

      boolean status =  verifyCartsWithinDateRange(cartDates, configReader.getProperty("startdate"), configReader.getProperty("enddate"));

      Assert.assertTrue(status);
        

    }

    @Test
    void getUserCartById(){

        given()
            .pathParam("userId", configReader.getProperty("userId"))
        .when()
            .get(Routes.GET_USER_CART)
        .then()
            .statusCode(200)
            .body("userId", everyItem(equalTo(configReader.getIntProperty("userId"))))
            .log().body();

    }

    @Test
    void getUserCartByLimit(){

        given()
            .pathParam("limit", configReader.getIntProperty("limit"))
        .when()
            .get(Routes.GET_CARTS_WITH_LIMIT)
        .then()
            .statusCode(200)
            .body("size()", lessThanOrEqualTo(configReader.getIntProperty("limit")))
            .log().body();

    }

    @Test
    void getCartsSortedDesceding(){

    Response response =    given()
            .pathParam("order", "desc")
        .when()
            .get(Routes.GET_CARTS_SORTED)
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .log().body()
            .extract().response();

        List<Integer> cartIds= response.jsonPath().getList("id");

        boolean status = isSortedDescending(cartIds);

        Assert.assertTrue(status);

    }

    @Test
    void getCartsSortedAscending(){

    Response response =    given()
            .pathParam("order", "asc")
        .when()
            .get(Routes.GET_CARTS_SORTED)
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .log().body()
            .extract().response();

        List<Integer> cartIds= response.jsonPath().getList("id");

        boolean status = isSortedAscending(cartIds);

        Assert.assertTrue(status);

    }

    @Test
    void createCart(){
     Cart cartsdata=   Payload.cartsPayload(1);

        given()
            .body(cartsdata)
            .contentType(ContentType.JSON)
        .when()
            .post(Routes.CREATE_CART)
        .then()
            .statusCode(200)
            .log().body();


    }

    @Test
    void updateCart(){
        Cart cartsdata= Payload.cartsPayload(1);
   
           given()
               .pathParam("id", 1)
               .body(cartsdata)
               .contentType(ContentType.JSON)
           .when()
               .patch(Routes.UPDATE_CART)
           .then()
               .statusCode(200)
               .log().body();
   
       }

       @Test
       void DeleteCart(){
         
              given()
                  .pathParam("id", 1)
              .when()
                  .delete(Routes.DELETE_CART)
              .then()
                  .statusCode(200)
                  .log().body();
      
          }



}
