package com.jaishna.billingcarerestframework.model.glCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
//@JsonIgnoreProperties
public class GLCodeRequest {

    private String glCode;
    private String glSegment;
    private String glPrefix;
    private String glDescription;
    private String glHistory;
    private String glStartDate;
    private String glEndDate;

}
