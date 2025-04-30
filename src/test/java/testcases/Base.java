package testcases;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import routes.Routes;
import utilities.ConfigReader;

public class Base {

    ConfigReader configReader;

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = Routes.BASE_URL;

        configReader = new ConfigReader();

    }

}
