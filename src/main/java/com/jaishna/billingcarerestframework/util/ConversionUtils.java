package com.jaishna.billingcarerestframework.util;

import com.portal.pcm.Poid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringTokenizer;

public class ConversionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionUtils.class);

    public static Poid strToPoid(String strPoid,Long dbNumber){
        long id = 0;
        String type = null;
        int revision = 0;
        StringTokenizer st = new StringTokenizer(strPoid,"+");
        if(st.countTokens() == 3) {
            while (st.hasMoreTokens()) {
                String dummy = st.nextToken();
                type = st.nextToken().trim();
                revision = ConversionUtils.getInteger(st.nextToken().trim());
            }
        }
        if(st.countTokens() == 4) {
            while (st.hasMoreTokens()) {
                String dummy = st.nextToken();
                type = st.nextToken().trim() + st.nextToken().trim();
                revision = ConversionUtils.getInteger(st.nextToken().trim());
            }
        }
        Poid poidObj = new Poid(dbNumber,revision,type);
        return poidObj;
    }

    public static Poid convertToPOID(Long dbNumber,Long value,String type) {
        Poid poidObj = new Poid(dbNumber,value,type);
        return poidObj;
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(.\\d+)?");
    }

    public static Date convertToTimeStamp(String value) {
        LOGGER.info("input value for Timestamp conversion: " + value);
        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = null;
        if(StringUtils.hasLength(value)) {
            timestamp = Timestamp.valueOf(df.format(new Date(value)));
            LOGGER.info("value of Timestamp : " + timestamp);
        }*/
       // Date timestamp = new Date(value);
        return new Date();
        //return timestamp;
    }

    public static BigDecimal getDecimal(String value) {
        LOGGER.info("input value for getDecimal conversion: " + value);
        if(StringUtils.hasLength(value) && isNumeric(value)) {
            return new BigDecimal(value);
        }
        else {
            throw new NumberFormatException("Invalid Input passed for Decimal attribute." + value);
        }
    }

    public static Integer getInteger(String value) {
        LOGGER.info("input value for getInteger conversion: " + value);
        if(StringUtils.hasLength(value) && isNumeric(value)) {
            return Integer.parseInt(value);
        }
        else {
            throw new NumberFormatException("Invalid Input passed Integer attribute." + value);
        }
    }

    public static Long getLong(String value) {
        LOGGER.info("input value for getInteger conversion: " + value);
        if(StringUtils.hasLength(value) && isNumeric(value)) {
            return Long.parseLong(value);
        }
        else {
            throw new NumberFormatException("Invalid Input passed Long attribute." + value);
        }
    }
}
