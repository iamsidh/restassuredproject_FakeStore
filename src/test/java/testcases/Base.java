package testcases;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import routes.Routes;

public class Base {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = Routes.BASE_URL;

    }

}
