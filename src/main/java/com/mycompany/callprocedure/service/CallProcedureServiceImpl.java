package com.mycompany.callprocedure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.callprocedure.dao.CallProcedureDataAccess;
import com.mycompany.web.exception.BusinessException;
import com.mycompany.web.forms.CallProcedureForm;

@Service("callProcedureServiceImpl")
public class CallProcedureServiceImpl implements CallProcedureService {

	@Autowired
	CallProcedureDataAccess dao;

	@Override
	public Boolean callProcedure(CallProcedureForm request)
			throws BusinessException {
		return dao.callProcedure(request);
	}

}
