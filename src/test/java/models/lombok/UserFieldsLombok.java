package models.lombok;

import lombok.Data;

@Data
public class UserFieldsLombok {

    private Boolean temporary;
    private String accountType;
    private ModificationReasonLombok modificationReason;
    private Boolean externalPassword;
    private Boolean active;
    private String addIdentity;
    private String login;
    private String blockEnd;
    private String ldapLogin;
    private String patronymic;
    private String password;
    private String blockBegin;
    private String surname;
    private String name;
    private String email;

}
