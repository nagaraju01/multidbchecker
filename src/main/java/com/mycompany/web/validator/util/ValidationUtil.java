/*
 * Copyright  © Solarcentury Holdings Ltd. All Rights Reserved.
 */
package com.mycompany.web.validator.util;

import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * ValidationUtil class
 *
 * @author nagarajut
 */
@Component
public class ValidationUtil {
	
	protected final Logger logger = Logger.getLogger(getClass());

    /**
     * default constructor
     */
    public ValidationUtil() {
    }

    /**
     * is valid date (yyyy/MM/dd HH:mm)
     *
     * @param date
     * @param usingSeconds
     * @return boolean
     */
    public boolean isValidDate(String date) {
        return DateUtils.isFormattedDate(date);
    }

    /**
     * Checks if the parameter only contains numeric data (no symbols or
     * letters)
     *
     * @param numericData
     * @return true if the data is correct
     */
    public boolean validateNumericData(String numericData) {

        if (numericData == null) {
            return false;
        }
        String numData = numericData.trim();
        if (numData.length() == 0) {
            return false;
        }

        try {
            Integer.parseInt(numData);
        } catch (NumberFormatException ex) {
            try {
                return !new Float(numData).isNaN();
            } catch (NumberFormatException ex1) {
                logger.error(ex1.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Converts {@link BindingResult} errors messages to collection. Only error
     * codes which are enclosed in "{}" brackets are included.
     *
     * @param result
     * @return list of error messages
     */
    public static Set<String> toErrorMessageList(BindingResult result) {
        Set<String> codeList = new HashSet<String>();
        for (Object object : result.getAllErrors()) {
            String defaultMessage = null;
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                defaultMessage = fieldError.getDefaultMessage();
            }
            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                defaultMessage = objectError.getDefaultMessage();
            }
            if (defaultMessage.startsWith("{") && defaultMessage.endsWith("}")) {
                defaultMessage = defaultMessage.replaceAll("(\\{|\\})", "");
                codeList.add(defaultMessage);
            }
        }
        return codeList;
    }

    /**
     * to Error Message List
     *
     * @param result
     * @return Set<String>
     */
    public static Set<String> toErrorMessageList(Errors result) {
        Set<String> codeList = new HashSet<String>();
        for (Object object : result.getAllErrors()) {
            String defaultMessage = null;
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                defaultMessage = fieldError.getDefaultMessage();
            }
            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                defaultMessage = objectError.getDefaultMessage();
            }
            if (defaultMessage.startsWith("{") && defaultMessage.endsWith("}")) {
                defaultMessage = defaultMessage.replaceAll("(\\{|\\})", "");
                codeList.add(defaultMessage);
            }
        }
        return codeList;
    }

    /**
     * Returns list of error codes
     *
     * @param result
     * @return Set<String>
     */
    public static Set<String> toErrorCodeList(BindingResult result) {
        Set<String> codeList = new HashSet<String>();
        for (ObjectError object : result.getAllErrors()) {
            codeList.add(object.getDefaultMessage());
        }
        return codeList;
    }

}
