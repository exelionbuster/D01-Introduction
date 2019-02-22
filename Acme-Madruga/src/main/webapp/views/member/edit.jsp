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

<form:form action="${actionURI}" modelAttribute="member">

	<form:hidden path="id" />
	<form:hidden path="version" />		
	<form:hidden path="enrolments" />
	<form:hidden path="requests" />
	<form:hidden path="userAccount.authorities" />
	
	<acme:textbox code="actor.name" path="name" />	
	<acme:textbox code="actor.middleName" path="middleName" />
	<acme:textbox code="actor.surname" path="surname" />
	
	<acme:textbox code="actor.email" path="email" />	
	<acme:textbox code="actor.photoURL" path="photo" />
	<acme:textbox code="actor.phoneNumber" path="phone" />
	<acme:textbox code="actor.address" path="address" />
	<br>
	<jstl:if test="${member.id == 0}">	
	<acme:textbox code="actor.username" path="userAccount.username" />
	<acme:password code="actor.password" path="userAccount.password" />
	</jstl:if>
	
	<acme:submit code="actor.submit" name="save" /> 	
	<acme:cancel url="welcome/index.do" code="actor.cancel"/>
	
	
</form:form>