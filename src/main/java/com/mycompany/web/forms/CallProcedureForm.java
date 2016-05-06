package com.mycompany.web.forms;

import java.text.ParseException;

import javax.validation.constraints.NotNull;

/**
 * Call procedure form on project call procedure page.
 *  
 * @author naga
 */
public class CallProcedureForm {
	
	@NotNull(message = "{project.callprocedure.environment.mandatory}")
	private String environment;
	
	@NotNull(message = "{project.callprocedure.environment.mandatory}")
	private String storedProcName;
	
	@NotNull(message = "{project.callprocedure.environment.mandatory}")
	private String afterDate;
	
	@NotNull(message = "{project.callprocedure.environment.mandatory}")
	private String afterTime;
	
	@NotNull(message = "{project.callprocedure.environment.mandatory}")
	private String beforeDate;
	
	@NotNull(message = "{project.callprocedure.environment.mandatory}")
	private String beforeTime;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getStoredProcName() {
		return storedProcName;
	}

	public void setStoredProcName(String storedProcName) {
		this.storedProcName = storedProcName;
	}

	public String getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	public String getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(String afterTime) {
		this.afterTime = afterTime;
	}

	public String getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(String beforeDate) {
		this.beforeDate = beforeDate;
	}

	public String getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(String beforeTime) {
		this.beforeTime = beforeTime;
	}
	
	
//				setCallAfter( NRGIUtils.getDateFromStringddMMyyyy(deadlineDate +" "+ afterTime) );
	
	
}
