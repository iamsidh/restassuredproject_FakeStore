package testcases;

import java.util.List;

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


    public boolean isSortedDescending(List<Integer> list) {

    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i) < list.get(i + 1)) {

        return false;
      }
    }
    return true;

  }

}
