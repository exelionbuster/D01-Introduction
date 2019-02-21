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

<form:form action="${actionURI}" modelAttribute="position">

	<form:hidden path="id" />
	<form:hidden path="version" />	
	<form:hidden path="language" />	
	
	<acme:textbox code="position.name" path=name" />

	
	<acme:submit code="position.submit" name="enrolmentSubmit" /> 	
	<acme:cancel url="position/administrator/list.do" code="position.cancel"/>
	
</form:form>
