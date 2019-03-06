<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="administrator.dashboard.table1" />
</p>

<table>

	<tr>
		<th></th>
		<th><spring:message code="administrator.dashboard.minimum" /></th>
		<th><spring:message code="administrator.dashboard.maximum" /></th>
		<th><spring:message code="administrator.dashboard.average" /></th>
		<th><spring:message code="administrator.dashboard.deviation" /></th>
	</tr>
	<tr>
		<td><jstl:out value="" /></td>
		<td><jstl:out value="" /></td>
		<td><jstl:out value="" /></td>
		<td><jstl:out value="" /></td>
	</tr>

</table>

<p>
	<spring:message code="administrator.dashboard.table2" />
</p>

<table>

	<tr>
		<td><spring:message code="administrator.dashboard.largest" /></td>
		<td><jstl:out value="" /></td>
	</tr>

	<tr>
		<td><spring:message code="administrator.dashboard.smallest" /></td>
		<td><jstl:out value="" /></td>
	</tr>

</table>


<p>
	<spring:message code="administrator.dashboard.table3" />
</p>

	<table>

		<tr>
			<th><spring:message code="administrator.dashboard.ratio" /></th>
			<th><spring:message code="administrator.dashboard.ratio" /></th>
			<th><spring:message code="administrator.dashboard.ratio" /></th>
			<th><spring:message code="administrator.dashboard.ratio" /></th>
		</tr>
		
		<tr>
			<td><spring:message code="administrator.dashboard.ratio" /></td>
			<td><jstl:out value="" /></td>
			<td><jstl:out value="" /></td>
			<td><jstl:out value="" /></td>
		</tr>

	</table>
	
	