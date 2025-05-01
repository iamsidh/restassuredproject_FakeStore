package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Products;
import routes.Routes;

@Listeners(utilities.ExtentReporter.class)
public class ProductDataDrivenTest extends Base {
    
    @Test(dataProvider = "jsonDataProvider",dataProviderClass = utilities.DataProviders.class)
    public void testAddNewProduct(Map<String,String> data){

    String title = data.get("title");
    double price = Double.parseDouble(data.get("price"));
    String description = data.get("description");
    String image = data.get("image");
    String category = data.get("category");

    Products productdata = new Products(title, price, description, image, category);
    
     Response response =  given()
            .contentType(ContentType.JSON)
            .body(productdata)
       .when()
            .post(Routes.CREATE_PRODUCT)
       .then()
            .log().body()
            .assertThat().body("id",notNullValue())
            .assertThat().body("title", containsString(productdata.getTitle()))
            .extract().response();

        int productId = response.jsonPath().getInt("id");
        System.out.println(productId);
    }

}
