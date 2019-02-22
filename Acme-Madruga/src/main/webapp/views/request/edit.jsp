<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${actionURI}" modelAttribute="request">

	<form:hidden path="id" />
	<form:hidden path="version" />		
	<form:hidden path="member" />
	<form:hidden path="procession" />
	
	<acme:textbox code="request.status" path="status" />	
	<acme:textarea code="request.rejectedReason" path="rejectedReason" />
	<acme:textbox code="request.processionRow" path="processionRow" />
	<acme:textbox code="request.processionColumn" path="processionColumn" />

		

	
	<acme:submit code="request.submit" name="requestSubmit" /> 	
	<acme:cancel url="request/member/list.do" code="request.cancel"/>
	
	
</form:form>
