/*
 \* Copyright  © Solarcentury Holdings Ltd. All Rights Reserved.
 */
package com.mycompany.web;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ControllerSupport class - Support for all controller, which wrapping all
 * common methods and functionality. Every controller should extend of this
 * class.
 *
 * @author naga
 */
public abstract class ControllerSupport {
	
    protected final Logger logger = Logger.getLogger(getClass());

    protected final String MODEL_KEY = "model";

    protected final String ERROR_MESSAGE_CODE_KEY = "errorMessageCode";

    protected final String ERROR_MESSAGE_KEY = "errorMessage";

    protected final String SUCCESS_MESSAGE_CODE_KEY = "successMessageCode";

    protected final String SUCCESS_MESSAGE_KEY = "successMessage";

    protected final String ERROR_MESSAGE_CODE_LIST_KEY = "errorMessageCodeList";

    protected final String ERROR_MESSAGE_LIST_KEY = "errorMessageList";

    protected String viewName;

    protected String viewDir = "";

    @Autowired
    protected HttpServletRequest request;

    /**
     * appends model and returns the view name
     *
     * @param modelMap
     * @param model
     * @return String
     */
    public String appendModelAndGetView(ModelMap modelMap, final Map<String, Object> model) {
        appendModel(modelMap, model);
        return getViewDir() + getViewName();
    }

    /**
     * appends model and redirects
     *
     * @param modelMap
     * @param model
     * @return String
     */
    public String appendModelAndRedirect(ModelMap modelMap, final Map<String, Object> model) {
        appendModel(modelMap, model);
        return "redirect:" + viewName;
    }

    /**
     * appends model and redirects setting parameters
     *
     * @param modelMap
     * @param model
     * @param redirectAttrs
     * @return String
     */
    public String appendModelAndRedirect(ModelMap modelMap, final Map<String, Object> model, RedirectAttributes redirectAttrs) {
        appendModel(modelMap, model);
        for (String key : model.keySet()) {
            redirectAttrs.addFlashAttribute(key, model.get(key));
        }
        return "redirect:" + viewName;
    }

    /**
     * Appends model. Model will be accessible in view under "model" key.
     *
     * e.g.
     * <pre>
     *  In controller:
     *  <code>
     *  	Map<String,Object> model = new HashMap<>();
     *  	model.put("value", "hello world");
     *  	appendModel(modelMap,  model);
     *  </code>
     *  In JSP page:
     *  <code>
     *  	${model.value}
     *  </code>
     * </pre>
     *
     * @param modelMap
     * @param model
     */
    protected void appendModel(ModelMap modelMap, final Map<String, Object> model) {
        modelMap.put(MODEL_KEY, model);
    }

    /**
     * Sets error code into given model map. Error will be shown in the top of
     * the body element.
     *
     * @param modelMap
     * @param errorCode - code of message defined in and of properties files.
     */
    public void setError(ModelMap modelMap, final String errorCode) {
        modelMap.put(ERROR_MESSAGE_CODE_KEY, errorCode);
    }

    /**
     * gets view name
     *
     * @return String
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * sets view name
     *
     * @param viewName
     */
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Add error code list
     *
     * @param errorCodeList
     * @param redirectAttrs
     */
    protected void addErrorCodeList(final List<String> errorCodeList, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute(ERROR_MESSAGE_CODE_LIST_KEY, errorCodeList);
    }

    /**
     * Sets error message and redirects to controller
     *
     * @param errorCode - code of error message defined in properties file
     * @param redirectAttrs - flash redirect attributes
     * @param url
     * @param ex
     * @return redirect to controller
     */
    protected String setErrorCodeAndReturnRedirect(final String errorCode, RedirectAttributes redirectAttrs, String url, Exception ex) {
        redirectAttrs.addFlashAttribute(ERROR_MESSAGE_CODE_KEY, errorCode);
        return redirect(url, ex);
    }

    /**
     * sets error message code and don't redirect
     *
     * @param errorCode
     * @param redirectAttrs
     */
    protected void setErrorMessageCode(final String errorCode, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute(ERROR_MESSAGE_CODE_KEY, errorCode);
    }

    /**
     * set list of error codes and redirect to specified url
     *
     * @param errorCodeList String list of error codes.
     * @param redirectAttrs an instance of RedirectAttributes
     * @param url String url
     * @param ex an Exception
     * @return
     */
    protected String setErrorCodesListAndReturnRedirect(final List<String> errorCodeList, RedirectAttributes redirectAttrs, String url, Exception ex) {

        String[] codesArray = new String[errorCodeList.size()];
        codesArray = errorCodeList.toArray(codesArray);

        redirectAttrs.addFlashAttribute(ERROR_MESSAGE_CODE_LIST_KEY, codesArray);
        return redirect(url, ex);

    }

    /**
     * Add error message
     *
     * @param messageList
     * @param redirectAttrs
     */
    protected void addErrorMessageList(final List<String> messageList, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute(ERROR_MESSAGE_LIST_KEY, messageList);
    }

    /**
     * Redirect to specified URL
     *
     * @param url
     * @param ex
     * @return String
     */
    protected String redirect(String url, Exception ex) {
        if (ex != null) {
            logger.error(ex.getMessage());
        }
        return "redirect:" + url;
    }

    /**
     * Redirect to specified URL showing a message
     *
     * @param message
     * @param redirectAttrs
     * @param url
     * @param ex
     * @return String
     */
    protected String setErrorMessageAndReturnRedirect(final String message, RedirectAttributes redirectAttrs, String url, Exception ex) {
        redirectAttrs.addFlashAttribute(ERROR_MESSAGE_KEY, message);
        return redirect(url, ex);
    }

    /**
     * Add error message
     *
     * @param message
     * @param redirectAttrs
     */
    protected void setErrorMessage(final String message, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute(ERROR_MESSAGE_KEY, message);
    }

    /**
     * set list of error messages and redirect to specified url to show the
     * messages.
     *
     * @param errors a list of FieldError objects
     * @param redirectAttrs an instance of RedirectAttributes
     * @param url String value
     * @param ex
     * @return
     */
    protected String setErrorMessageAndReturnRedirect(List<FieldError> errors, RedirectAttributes redirectAttrs, String url, Exception ex) {

        List<String> codeList = new ArrayList<String>();
        for (Object object : errors) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                codeList.add(fieldError.getDefaultMessage());

            }

        }
        setErrorCodesListAndReturnRedirect(codeList, redirectAttrs, url, null);
        return redirect(url, ex);

    }

    /**
     * sets success message code
     *
     * @param successCode
     * @param redirectAttrs
     */
    protected void setSuccessMessageCode(final String successCode, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE_CODE_KEY, successCode);
    }

    /**
     * sets success message code
     *
     * @param modelMap
     * @param successCode
     */
    protected void setSuccessMessageCode(ModelMap modelMap, final String successCode) {
        modelMap.put(SUCCESS_MESSAGE_CODE_KEY, successCode);
    }

    /**
     * sets success message
     *
     * @param message
     * @param redirectAttrs
     */
    protected void setSuccessMessage(final String message, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE_KEY, message);
    }

    /**
     * Sets success message and redirect
     *
     * @param message
     * @param redirectAttrs
     * @param url
     * @return redirect URL
     */
    protected String setSuccessMessageAndRedirect(final String message, RedirectAttributes redirectAttrs, String url) {
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE_KEY, message);
        return redirect(url, null);
    }

    /**
     * Method to report any validation error to the user. We don't need to use
     * the audit log for validation errors.
     *
     * @param result BindingResult object that contains the errors.
     * @param redirectPath Path to redirect to
     * @param redirectAttrs
     * @return
     */
    protected String addValidationError(BindingResult result, String redirectPath, RedirectAttributes redirectAttrs) {
        FieldError err = result.getFieldError();
        return setErrorMessageAndReturnRedirect(err.getDefaultMessage(), redirectAttrs, redirectPath, null);
    }

    protected String getViewDir() {
        return viewDir;
    }

    protected void setViewDir(String viewDir) {
        this.viewDir = viewDir;
    }

    /**
     * redirects to mapping URL
     *
     * @param mappingUrl
     * @return String
     */
    public static String redirectTo(String mappingUrl) {
        return "redirect:" + mappingUrl;
    }

    /**
     * validates the form object
     *
     * @param o
     * @param validator
     * @param result
     * @param redirectAttrs
     * @param redirectUrl
     * @return String
     */
    public String validate(Object o, Validator validator, BindingResult result,
            RedirectAttributes redirectAttrs, String redirectUrl) {
        validator.validate(o, result);
        return redirectTo(result, redirectAttrs, redirectUrl);
    }

    protected String redirectTo(BindingResult result, RedirectAttributes redirectAttrs, String redirectUrl) {

        if (result.hasErrors()) {
            try {
                List<String> messages = getErrorMessages(result);
                addErrorMessageList(messages, redirectAttrs);
                return redirect(redirectUrl, null);

            } catch (NoSuchMessageException ex) {
                return setErrorMessageAndReturnRedirect(
                        ex.getMessage(),
                        redirectAttrs, redirectUrl, null);
            }
        }
        // when no errors
        return null;
    }

    protected List<String> getErrorMessages(BindingResult result) {

        List<String> messages = new ArrayList<String>();

        if (result.hasErrors()) {

            try {

                // handle field errors
                for (FieldError err : result.getFieldErrors()) {
                    messages.add(err.getDefaultMessage());
                }

                // handle global errors
                for (ObjectError err : result.getGlobalErrors()) {
                	messages.add(err.getDefaultMessage());
                }

            } catch (NoSuchMessageException ex) {
                messages.add(ex.getMessage());
            }
        }

        return messages;
    }

}
