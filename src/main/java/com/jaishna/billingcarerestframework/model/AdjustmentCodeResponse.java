package com.jaishna.billingcarerestframework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties
public class AdjustmentCodeResponse {

    private String errorCode;
    private String errorDescription;
    private String requestStatus;
    private List<SearchResult> results = new ArrayList<>();

}
