/*
 * Copyright  © mycompany. All Rights Reserved.
*/
package com.mycompany.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.web.forms.CallProcedureForm;

/**
 * CallProcedureFormValidator class
 *
 * @author naga
 */
@Component
public class CallProcedureFormValidator implements Validator {

    @Override
    public boolean supports(Class calzz) {
        return CallProcedureForm.class.equals(calzz);
    }

    /**
     * validates the request
     *
     * @param obj
     * @param errors
     */
    @Override
    public void validate(Object obj, Errors errors) {

    	CallProcedureForm form = (CallProcedureForm) obj;

    }

}
