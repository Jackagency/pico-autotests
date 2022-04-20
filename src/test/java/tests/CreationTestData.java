package tests;

import com.beust.ah.A;
import models.lombok.ModificationReasonLombok;
import models.lombok.UserFieldsLombok;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.json.Json;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static listeners.CustomAllureListener.withCustomTemplates;

public class CreationTestData {
    @Test
    public void userCreate() {
        UserFieldsLombok userFields = new UserFieldsLombok();
        ModificationReasonLombok modificationReason = new ModificationReasonLombok();
        AuthToken authToken = new AuthToken();
        authToken.picoGetToken();

        modificationReason.setReconciliationDate(null);
        modificationReason.setReconciliationUser("");
        modificationReason.setReasonDate("2022-04-08T11:33:56");
        modificationReason.setReason("reason-n");
        modificationReason.setComment("reason-c");
        modificationReason.setReasonType("reason-t");

        userFields.setName("Петр");
        userFields.setSurname("Петров");
        userFields.setPatronymic("Петрович");
        userFields.setLogin("petrovich");
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


        given()
                .filter(withCustomTemplates())
                .header("Authorization", "Bearer " + authToken.picoGetToken())
                .body(userFields)
                .contentType(JSON)
                .body(userFields)
                .post("http://192.168.151.19:8080/pico/api/v1/users")
                .then()
                .log().body()
                .log().status()
                .statusCode(200);


    }
}


