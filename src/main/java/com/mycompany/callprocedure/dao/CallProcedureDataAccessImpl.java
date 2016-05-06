/*
 \* Copyright  ©  MyCompany. All Rights Reserved.
 */
package com.mycompany.callprocedure.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.web.exception.BusinessException;
import com.mycompany.web.forms.CallProcedureForm;

@Repository("callProcedureDataAccessImpl")
public class CallProcedureDataAccessImpl implements
		CallProcedureDataAccess {

	private static final String CUSTOMER_SPROC = "SOLAR.GET_CUSTOMER";

	@Autowired
	@Qualifier("jdbcTemplateDev") 
	private JdbcTemplate jdbcTemplateDev;
	
	@Autowired
	@Qualifier("jdbcTemplateQa") 
	private JdbcTemplate jdbcTemplateQa;
	
	@Autowired
	@Qualifier("jdbcTemplateStaging") 
	private JdbcTemplate jdbcTemplateStaging;

	@Override
	public Boolean callProcedure(CallProcedureForm request)
			throws BusinessException {
		setDataSource(jdbcTemplateDev);
		return get(0l);
	}

	public Boolean get(Long accountNumber) throws BusinessException {
		if (accountNumber == null) {
			throw new IllegalArgumentException();
		}
		if (getJdbcTemplate() == null) {
			throw new BusinessException("DB does not exist");
		}
		try {
			CustomerStoredProcedure proc = new CustomerStoredProcedure(
					getJdbcTemplate().getDataSource(), CUSTOMER_SPROC);
			Map results = proc.execute();
			List customers = (List) results.get("rs");
			if (CollectionUtils.isNotEmpty(customers)) {
				// return the first one
				return (Boolean) customers.get(0);
			}
			return null;
		} catch (DataAccessException ex) {
			throw new BusinessException("Data access error ");
		}
	}

	/**
	 * * Spring DI for the template. instantiates the JdbcTemplate * @param
	 * template
	 */
	public void setDataSource(JdbcTemplate template) {
		this.jdbcTemplateDev = template;
	}

	/** * @return the jdbcTemplate */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplateDev;
	}

}
