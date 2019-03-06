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

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="requests" id="row" requestURI="${actionURI}"
	pagesize="5" class="displaytag">

	<display:column property="procession.title" titleKey="request.procession" />
	<display:column property="member.name" titleKey="request.member" />
	<display:column property="status" titleKey="request.status" />
	
	<jstl:if test="">
		<display:column property="rejectedReason"
			titleKey="request.rejectedReason" />
	</jstl:if>
	<jstl:if test="">
		<display:column property="processionRow"
			titleKey="request.processionRow" />
		<display:column property="processionColumn"
			titleKey="request.processionColumn" />
	</jstl:if>

	<security:authorize access="hasRole('BROTHERHOOD')">
		<acme:edit titleKey="request.edit" code="request.edit"
			url="request/brotherhood/edit.do?Id=${id}" />
	</security:authorize>

</display:table>
