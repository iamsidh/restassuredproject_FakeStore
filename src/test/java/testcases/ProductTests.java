package testcases;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import routes.Routes;

public class ProductTests extends Base {

  // 1. Test to fetch all the products
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

  //2 Test to fetch single product by Id
  @Test
  public void testGetSingleProductById() {

    int productId = configReader.getIntProperty("productId");

    given()
        .pathParam("id", productId)
        .when()
        .get(Routes.GET_PRODUCT_BY_ID)
        .then()
        .assertThat().statusCode(200)
        .body("category", containsString("men"))
        .log().body();

  }

  //3. Test to get products by limit value
  @Test
  public void testGetLimitedProducts() {

    int limit = configReader.getIntProperty("limit");
    given()
        .pathParam("limit", limit)
        .when()
        .get(Routes.GET_PRODUCTS_WITH_LIMIT)
        .then()
        .statusCode(200)
        .body("size()", equalTo(limit))
        .log().body();

  }

  //4. Test to fetch products in descending order
  @Test
  public void testGetSortedProductsDesceding() {

    Response response = given()
        .when()
            .pathParam("order", "desc")
        .get(Routes.GET_PRODUCTS_SORTED)
        .then()
            .statusCode(200)
            .extract().response();

  List<Integer> productIds = response.jsonPath().getList("id", Integer.class);

  boolean status = isSortedDescending(productIds);

  Assert.assertTrue(status);

  }

  //5. Test to fetch products in ascending order
  @Test
  public void testGetSortedProductsAscending() {

    Response response = given()
        .when()
            .pathParam("order", "asc")
        .get(Routes.GET_PRODUCTS_SORTED)
        .then()
            .statusCode(200)
            .extract().response();

  List<Integer> productIds = response.jsonPath().getList("id", Integer.class);

  boolean status = isSortedDescending(productIds);

  assertThat(status, is(false));

  }


}
