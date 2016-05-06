package com.mycompany.callprocedure.service;

import com.mycompany.web.exception.BusinessException;
import com.mycompany.web.forms.CallProcedureForm;

public interface CallProcedureService {

	Boolean callProcedure(CallProcedureForm request) throws BusinessException;
	
}
