package models;

public class GenerateTokenResponse {
        /*
        {
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0OTQwNzA5OCwiZXhwIjoxNjQ5NDEyNjk4fQ.CfugNieeluzje20_UH-xQkhmSqcTWLTAzDPtgzuR4k6dy4xatfx5m3x0nW4gwOLaK7KBwIXI_aN2xEYIYJryKg",
    "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0OTQwNzA5OCwiZXhwIjoxNjQ5OTcwMDAwfQ.IMDpEjQgv-jS36gSj8XChvzIBe7Qu2vpZY1iP9vU1_hozRfcZH1jE2VlkqsaeXzCvceAHG07QY_Nwz76_7Eo6Q",
    "expiredDate": 1649412698229
        }
        */

    private String accessToken;
    private String refreshToken;
    private String expiredDate;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getExpiredDate() {
        return expiredDate;
    }
}

