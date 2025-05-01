package testcases;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Products;

import static io.restassured.RestAssured.given;
import routes.Routes;

public class ProductTests extends Base {

  // 1. Test to fetch all the products
  @Test(priority = 0)
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
  @Test(priority = 1)
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
  @Test(priority = 2)
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
  @Test(priority = 3)
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
  @Test(priority = 4)
  public void testGetSortedProductsAscending() {

    Response response = given()
        .when()
            .pathParam("order", "asc")
        .get(Routes.GET_PRODUCTS_SORTED)
        .then()
            .statusCode(200)
            .extract().response();

  List<Integer> productIds = response.jsonPath().getList("id", Integer.class);

  boolean status = isSortedAscending(productIds);

  Assert.assertTrue(status);
  //assertThat(status, is(false));

  }

  //6. Test to fetch all product categories

  @Test(priority = 5)
  public void testGetAllCategories(){

    given()
    .when()
      .get(Routes.GET_ALL_CATEGORIES)
    .then()
      .statusCode(200)
      .body("size()", greaterThan(0))
      .log().body();

  }

  //7. Test to fetch products by category
  @Test(priority = 6)
  public void testProductsByCategory(){

    String category = configReader.getProperty("category");

    given()
      .pathParam("category", category)
    .when()
      .get(Routes.GET_PRODUCT_BY_CATEGORY)
    .then()
      .statusCode(200)
      .assertThat().contentType(ContentType.JSON)
      .body("size()", greaterThan(0))
      .body("category", everyItem(notNullValue()))
      .body("category", everyItem(equalTo(category)))
      .log().body();
  }

  //8. Test to Add new product

  @Test(priority = 7)
  public void testAddNewProduct(){
    
    Products data =Payload.productPayload();
    
     Response response =  given()
            .contentType(ContentType.JSON)
            .body(data)
       .when()
            .post(Routes.CREATE_PRODUCT)
       .then()
            .log().body()
            .assertThat().body("id",notNullValue())
            .assertThat().body("title", containsString(data.getTitle()))
            .extract().response();

        int productId = response.jsonPath().getInt("id");

  }

  //9. Update an existing product
  @Test(priority = 8)
  public void testUpdateProduct(){

    Products data = Payload.productPayload();

    given()
        .contentType(ContentType.JSON)
        .body(data)
        .pathParam("id", configReader.getIntProperty("productId"))
    .when()
        .put(Routes.UPDATE_PRODUCT)
    .then()
        .assertThat().statusCode(200)
        .assertThat().body("title", equalTo(data.getTitle()))
        .log().body();

  }

  //10. test to delete a product
  @Test(priority = 9)
  public void testDeleteProduct(){

    given()
        .pathParam("id", configReader.getIntProperty("productId"))
    .when()
        .delete(Routes.DELETE_PRODUCT)
    .then()
        .statusCode(200);

  }

}
