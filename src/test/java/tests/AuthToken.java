package tests;

import static io.restassured.RestAssured.given;
import static listeners.CustomAllureListener.withCustomTemplates;

public class AuthToken {

    public String picoGetToken() {

        return given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "admin")
                .formParam("password", "123")
                .post("http://192.168.151.19:8080/pico/api/v1/auth")
                .then()
                .log().status()
                .statusCode(200)
                .extract().path("accessToken");
    }
}
