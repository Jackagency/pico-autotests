package tests;

import com.github.javafaker.Faker;
import models.lombok.ModificationReasonLombok;
import models.lombok.UserFieldsLombok;
import utils.RandomUtils;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static listeners.CustomAllureListener.withCustomTemplates;

public class CreationTestData {

    public String userCreate() {
        UserFieldsLombok userFields = new UserFieldsLombok();
        ModificationReasonLombok modificationReason = new ModificationReasonLombok();
        AuthToken authToken = new AuthToken();
        authToken.picoGetToken();

        RandomUtils randomUtils = new RandomUtils();

        String word = String.valueOf(randomUtils.randomString());

        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().firstName();
        String surname = faker.name().lastName();


        modificationReason.setReconciliationDate(null);
        modificationReason.setReconciliationUser("");
        modificationReason.setReasonDate("2022-04-08T11:33:56");
        modificationReason.setReason("reason-n");
        modificationReason.setComment("reason-c");
        modificationReason.setReasonType("reason-t");

        userFields.setName(name);
        userFields.setSurname(surname);
        userFields.setPatronymic(word);
        userFields.setLogin(word);
        userFields.setLdapLogin("");
        userFields.setPassword("123");
        userFields.setExternalPassword(false);
        userFields.setEmail("123@123.ru");
        userFields.setActive(true);
        userFields.setAccountType("NONE");
        userFields.setAddIdentity(null);
        userFields.setTemporary(false);
        userFields.setBlockBegin("");
        userFields.setBlockEnd("");
        userFields.setModificationReason(modificationReason);

        String login =
                given()
                        .filter(withCustomTemplates())
                        .header("Authorization", "Bearer " + authToken.picoGetToken())
                        .body(userFields)
                        .contentType(JSON)
                        .body(userFields)
                        .post("http://192.168.151.19:8080/pico/api/v1/users")
                        .then()
                        .log().status()
                        .statusCode(201)
                        .extract().path("login");
        System.out.println("Пользователь с логином: " + login + " создан");
        return login;
    }
}


