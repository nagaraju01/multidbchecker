package com.mycompany.callprocedure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * * * CustomerStoredProcedure provides processing to call SPROC for getting
 * customers
 */
class CustomerStoredProcedure extends StoredProcedure {

	private static final String ACCOUNTNUMBER_PARAM = "@ACCTNUM";

	public CustomerStoredProcedure(DataSource dataSource, String sprocName) {
		super(dataSource, sprocName);
		declareParameter(new SqlReturnResultSet("rs", new ResultMapper()));
		declareParameter(new SqlParameter(ACCOUNTNUMBER_PARAM, Types.NUMERIC));
		compile();
	}

	public Map execute(Long accountNumber) {
		Map inputs = new HashMap();
		inputs.put(ACCOUNTNUMBER_PARAM, accountNumber);
		return super.execute(inputs);
	}

	/**
	 * * CustomerMapper processes a ResultSet to populate a Customer object * @author
	 * stevi.deter@gmail.com * @version %I%, %G% * @since 1.0 *
	 */
	protected static final class ResultMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Customer customer = new Customer();
//			customer.setAccountNumber(new Long(rs.getLong("ACCOUNTNUMBER")));
//			customer.setLastName(StringUtils.trimToNull(rs
//					.getString("LASTNAME")));
//			customer.setFirstName(StringUtils.trimToNull(rs
//					.getString("FIRSTNAME")));
			return Boolean.TRUE;
		}
	}
}
