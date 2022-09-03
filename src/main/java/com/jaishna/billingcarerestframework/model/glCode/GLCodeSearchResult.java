package com.jaishna.billingcarerestframework.model.glCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties
public class GLCodeSearchResult {

    private String glSegment;
    private String createdDate;
    private String modifiedDate;
    private String glDescription;
    private String glid;
    private String attributeName;
    private String orderId;
    private String code;
    private String glAccount;
    private String stringValue;
    private String codeString;
    private String accountCode;
    private String status;

}
