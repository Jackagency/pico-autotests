package models.lombok;

import lombok.Data;

@Data
public class ModificationReasonLombok {
    private String reason;
    private String reconciliationUser;
    private String reconciliationDate;
    private String reasonDate;
    private String comment;
    private String reasonType;
}
