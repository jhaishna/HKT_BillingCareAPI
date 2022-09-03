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
public class SearchResult {

    private String createdDate;
    private String adjustmentCode;
    private String englishDescription;
    private String chineseDescription;
    private String glid;

}
