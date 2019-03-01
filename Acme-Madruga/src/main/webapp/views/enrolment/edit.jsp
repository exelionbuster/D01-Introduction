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

<form:form action="${actionURI}" modelAttribute="enrolment">

	<form:hidden path="id" />
	<form:hidden path="version" />		
	<form:hidden path="member" />
	<form:hidden path="brotherhood" />
	

	
	<jstl:choose>
	<jstl:when test="${locale eq 'en'}">		
	<acme:select path="position" code="enrolment.position" items="${positions}" itemLabel="${name}" />
	
	</jstl:when>
	<jstl:when test="${locale eq 'es'}">	
	<acme:select path="position" code="enrolment.position" items="${positions}" itemLabel="${name}" />				
	</jstl:when>
	</jstl:choose>	
	

	
			
	<acme:submit code="enrolment.submit" name="save" /> 	
	<acme:cancel url="enrolment/brotherhood/list.do" code="enrolment.cancel"/>	
	
</form:form>


