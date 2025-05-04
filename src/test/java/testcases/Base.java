package testcases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;
import utilities.ConfigReader;

public class Base {
  RequestLoggingFilter requestLoggingFilter;
  ResponseLoggingFilter responseLoggingFilter;
  ConfigReader configReader;

  @BeforeClass
  public void setup() throws FileNotFoundException {

    RestAssured.baseURI = Routes.BASE_URL;

    configReader = new ConfigReader();

    RestAssured.baseURI = Routes.BASE_URL;

    // Setup filters for logging
    File file = new File("./logs/test-results.txt");
    FileOutputStream fos = new FileOutputStream(file);
    PrintStream log = new PrintStream(fos, true);
    requestLoggingFilter = new RequestLoggingFilter(log);
    responseLoggingFilter = new ResponseLoggingFilter(log);

    RestAssured.filters(requestLoggingFilter, responseLoggingFilter);

  }

  public boolean isSortedDescending(List<Integer> list) {

    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i) < list.get(i + 1)) {

        return false;
      }
    }
    return true;

  }

  public boolean isSortedAscending(List<Integer> list) {

    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i) > list.get(i + 1)) {

        return false;
      }
    }
    return true;

  }

  public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public boolean verifyCartsWithinDateRange(List<String> cartdate, String startdate, String enddate) {

    LocalDate startDate = LocalDate.parse(startdate, formatter);
    LocalDate enddDate = LocalDate.parse(enddate, formatter);

    for (String dateTime : cartdate) {

      LocalDate cartDate = LocalDate.parse(dateTime.substring(0, 10), formatter);

      if (cartDate.isBefore(startDate) || cartDate.isAfter(enddDate)) {
        return false;

      }

    }

    return true;

  }

}
