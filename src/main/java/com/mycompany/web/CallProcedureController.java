/*
 \* Copyright  mycompany. All Rights Reserved.
 */
package com.mycompany.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.callprocedure.service.CallProcedureService;
import com.mycompany.web.exception.BusinessException;
import com.mycompany.web.forms.CallProcedureForm;
import com.mycompany.web.validator.CallProcedureFormValidator;
import com.mycompany.web.validator.util.ValidationUtil;

/**
 * CallProcedureController class - Call procedure controller which handle
 * request from call procedure page
 *
 * @author naga
 */
@Controller
public class CallProcedureController extends ControllerSupport {

	public static final String MODEL_ATTRIBUTE = "callProcedure";

	public static final String MAPPING_URL = "/callProcedure";
	
//    @Autowired
//    private CallProcedureFormValidator validator;
    
    @Autowired
    private CallProcedureService callProcedureService;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.addValidators(validator);
//    }
    
    @RequestMapping(value = "/callProcedure", method = RequestMethod.GET)
    public String viewCallProcedure(Map<String, Object> model) {
    	CallProcedureForm callProcedureForm = new CallProcedureForm();    
        model.put("callProcedure", callProcedureForm);
      
        return CallProcedureController.MAPPING_URL;
    }

	@RequestMapping(value = "/callProcedure", method = RequestMethod.POST)
	public String handleCallProcedure(
			final ModelMap map,
			final @ModelAttribute(MODEL_ATTRIBUTE) CallProcedureForm form,
			final BindingResult result, final HttpServletRequest request,
			final RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
//			redirectAttrs.addFlashAttribute(ERROR_MESSAGE_CODE_LIST_KEY,
//					ValidationUtil.toErrorMessageList(result));
			return CallProcedureController.redirectTo(CallProcedureController.MAPPING_URL);
		}

		try {
			// call service from here
			callProcedureService.callProcedure(form);
			
			setSuccessMessageCode("project.callprocedure.creation.success",
					redirectAttrs);
		} catch (BusinessException ex) {
			return "error naga...";
		}

		return "redirect:" + CallProcedureController.MAPPING_URL;
	}

}
