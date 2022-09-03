package com.jaishna.billingcarerestframework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties
public class AdjustmentCodeRequest {

    private String reasonCode;
    private String glid;
    private String description;
    private String prefixAdjustment;

}
