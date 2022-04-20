package models.lombok;

import lombok.Data;

@Data
public class GenerateTokenResponseLombok {

    private String accessToken;
    private String refreshToken;
    private String expiredDate;

}
