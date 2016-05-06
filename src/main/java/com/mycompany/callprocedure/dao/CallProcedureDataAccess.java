/*
 \* Copyright  ©  MyCompany. All Rights Reserved.
 */
package com.mycompany.callprocedure.dao;

import com.mycompany.web.exception.BusinessException;
import com.mycompany.web.forms.CallProcedureForm;

/**
 * CallProcedureDataAccess interface
 *
 * @author Nagarajut
 */
public interface CallProcedureDataAccess {

	Boolean callProcedure(CallProcedureForm request) throws BusinessException;

}
