package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerExample {
//пример работы фейкера
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en"));
        String name = faker.name().firstName();

        System.out.println(name);
    }
}
