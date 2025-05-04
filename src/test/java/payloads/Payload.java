package payloads;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Geolocation;
import pojo.Name;
import pojo.Products;
import pojo.Users;

public class Payload {

    private static final Faker faker = new Faker();
    private static final String categories[] = { "electornics", "furniture", "clothing", "books", "beauty" };
    private static final Random random = new Random();

    public static Products productPayload() {

        String name = faker.commerce().productName();

        double price = Double.parseDouble(faker.commerce().price());

        String description = faker.lorem().sentence();

        String imageUrl = "https://i.pravatar.cc/100";

        String category = categories[random.nextInt(categories.length)];

        return new Products(name, price, description, imageUrl, category);
    }

    public static Users usersPayload() {

        // name
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Name name = new Name(firstName, lastName);

        // location
        String lattittude = faker.address().latitude();
        String longitude = faker.address().longitude();
        Geolocation geolocation = new Geolocation(lattittude, longitude);

        // address
        String city = faker.address().city();
        String street = faker.address().streetName();
        int number = random.nextInt(100);
        String zipcode = faker.address().zipCode();
        Address address = new Address(city, street, number, zipcode, geolocation);

        String email = faker.internet().emailAddress();
        String username = faker.name().username();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().cellPhone();

        Users user = new Users(email, username, password, name, address, phone);
        return user;
    }

}
