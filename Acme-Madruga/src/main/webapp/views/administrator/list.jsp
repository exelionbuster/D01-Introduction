<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">

	<p>
		<spring:message code="administrator.dashboard.table1" />
	</p>

	<table>

		<%-- AQUI SE TRATA LAS ESTADISITCAS DEL DASHBOARD, UN EJEMPLO
		
		<tr>
			<th></th>
			<th><spring:message code="administrator.dashboard.minimum" /></th>
			<th><spring:message code="administrator.dashboard.maximum" /></th>
			<th><spring:message code="administrator.dashboard.average" /></th>
			<th><spring:message code="administrator.dashboard.deviation" /></th>
		</tr>
 --%>
	</table>



</security:authorize>