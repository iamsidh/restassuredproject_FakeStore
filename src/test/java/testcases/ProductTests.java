package testcases;

import static org.hamcrest.Matchers.greaterThan;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import routes.Routes;

public class ProductTests extends Base {

    //1. Test to fetch all the products
    @Test
    public void testGetAllProducts() {

        given()
                .when()
                .get(Routes.GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .log().body();

    }

    @Test
    public void getSingleProductById(){

      int productId =  configReader.getIntProperty("productId");

      given()
        .pathParam("id", productId)
      .when()
        .get(Routes.GET_PRODUCT_BY_ID)
      .then()
        .log().body();

    }

}
