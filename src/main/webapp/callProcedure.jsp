<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<html>

<body>
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
		

	<form:form modelAttribute="callProcedure" method="post" id="callProcedureForm">
          
		Environment : <input type="text" class="environment"
			name="environment" />
		</br>
		Stored Procedure :<input type="text" class="storedProcName"
			name="storedProcName" />
		</br>
		After Date:	<input type="text" class="datepicker" name="afterDate"
			value="" />
		</br>
		After Time:	<input type="text" class="timepicker" name="afterTime"
			value="" />
		</br>
		Before Date: <input type="text" class="datepicker" name="beforeDate"
			value="" />
		</br>
		Before Time: <input type="text" class="timepicker" name="beforeTime"
			value="" />
		</br>

		<button type="submit" id="callTrigger">Call</button>
		
		<input type="submit" title="Call stored procedure" value="Call">
		
</form>
<!-- 
	</form:form>
	 -->
	<div id="basepath">http://localhost:8080/VM</div>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	 
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="js/parsley.js"></script>
	<script src="js/parsley.min.js"></script>
	<script src="js/callprocedure.js"></script>

</body>


</html>