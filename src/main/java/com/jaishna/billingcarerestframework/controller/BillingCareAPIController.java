package com.jaishna.billingcarerestframework.controller;

import com.jaishna.billingcarerestframework.model.AdjustmentCodeRequest;
import com.jaishna.billingcarerestframework.model.AdjustmentCodeResponse;
import com.jaishna.billingcarerestframework.model.glCode.GLCodeRequest;
import com.jaishna.billingcarerestframework.model.glCode.GLCodeResponse;
import com.jaishna.billingcarerestframework.service.BillingCareAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillingCareAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillingCareAPIController.class);

    @Autowired
    private BillingCareAPIService billingCareAPIService;

    @RequestMapping(value = "/adjustmentCodeSearch", method = RequestMethod.POST,consumes = "application/json",produces="application/json")
    public ResponseEntity<?> adjustmentCodeSearch(@RequestBody AdjustmentCodeRequest adjustmentCodeRequest, @RequestHeader HttpHeaders headers) {
        LOGGER.info("adjustmentCodeSearch..");
        AdjustmentCodeResponse adjustmentCodeResponse = new AdjustmentCodeResponse();
        boolean valid = validateRequest(adjustmentCodeRequest,adjustmentCodeResponse);
        if(!valid) {
            adjustmentCodeResponse.setErrorDescription("Validation error. Missing required fields in the input.");
            adjustmentCodeResponse.setErrorCode("400");
            return new ResponseEntity<>(adjustmentCodeResponse, HttpStatus.BAD_REQUEST);
        }
        adjustmentCodeResponse = billingCareAPIService.getAdjustmentCodeResults(adjustmentCodeRequest,adjustmentCodeResponse);
        LOGGER.info("Response returned from BRM DB :"+adjustmentCodeResponse);
        if(adjustmentCodeResponse!=null && adjustmentCodeResponse.getErrorDescription()!=null) {
            return new ResponseEntity<>(adjustmentCodeResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(adjustmentCodeResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/glCodeMaintenanceSearch", method = RequestMethod.POST,consumes = "application/json",produces="application/json")
    public ResponseEntity<?> glCodeMaintenanceSearch(@RequestBody GLCodeRequest glCodeRequest, @RequestHeader HttpHeaders headers) {
        LOGGER.info("glCodeMaintenanceSearch..");
        GLCodeResponse glCodeResponse = new GLCodeResponse();
        boolean valid = validateGLCodeRequest(glCodeRequest,glCodeResponse);
        if(!valid) {
            glCodeResponse.setErrorDescription("Validation error. Missing required fields in the input.");
            glCodeResponse.setErrorCode("400");
            return new ResponseEntity<>(glCodeResponse, HttpStatus.BAD_REQUEST);
        }
        glCodeResponse = billingCareAPIService.getGLCodeResults(glCodeRequest,glCodeResponse);
        LOGGER.info("Response returned from BRM DB :"+glCodeResponse);
        if(glCodeResponse!=null && glCodeResponse.getErrorDescription()!=null) {
            return new ResponseEntity<>(glCodeResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(glCodeResponse, HttpStatus.OK);
    }

    private boolean validateRequest(AdjustmentCodeRequest adjustmentCodeRequest,AdjustmentCodeResponse adjustmentCodeResponse) {
        if(!StringUtils.hasLength(adjustmentCodeRequest.getReasonCode()) && !StringUtils.hasLength(adjustmentCodeRequest.getPrefixAdjustment())
                && !StringUtils.hasLength(adjustmentCodeRequest.getDescription()) && !StringUtils.hasLength(adjustmentCodeRequest.getGlid())) {
            adjustmentCodeResponse.setErrorDescription("Validation error. Missing required fields in the input.");
            return false;
        }
        return true;
    }

    private boolean validateGLCodeRequest(GLCodeRequest glCodeRequest,GLCodeResponse glCodeResponse) {
        if(!StringUtils.hasLength(glCodeRequest.getGlCode()) && !StringUtils.hasLength(glCodeRequest.getGlSegment()) && !StringUtils.hasLength(glCodeRequest.getGlPrefix())
        && !StringUtils.hasLength(glCodeRequest.getGlDescription()) && !StringUtils.hasLength(glCodeRequest.getGlHistory()) && !StringUtils.hasLength(glCodeRequest.getGlStartDate())
        && !StringUtils.hasLength(glCodeRequest.getGlEndDate())) {
            glCodeResponse.setErrorDescription("Validation error. Missing required fields in the input.");
            return false;
        }
        return true;
    }

}
