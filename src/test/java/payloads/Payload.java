package payloads;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Products;

public class Payload {

    private static final Faker faker = new Faker();
    private static final String categories[]={"electornics","furniture","clothing","books","beauty"};
    private static final Random random = new Random();

   public static Products productPayload() {

        String name = faker.commerce().productName();

        double price = Double.parseDouble(faker.commerce().price());

        String description = faker.lorem().sentence();

        String imageUrl = "https://i.pravatar.cc/100";

        String category = categories[random.nextInt(categories.length)];

        return new Products(name, price, description, imageUrl, category);
    }

}
