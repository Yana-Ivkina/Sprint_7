package Courier;

import com.github.javafaker.Faker;

public class CourierGenerator {

    public static CreateCourier courierDefault() {
        return new CreateCourier("ninja", "1234", "saske");
    }

    public static CreateCourier courierRandom() {
        Faker faker = new Faker();
        return new CreateCourier(faker.name().username(), faker.number().digits(4), faker.name().firstName());
    }
}
