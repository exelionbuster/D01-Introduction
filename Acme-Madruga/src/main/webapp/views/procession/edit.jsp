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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form action="${actionURI}" modelAttribute="procession">

	<form:hidden path="id" />
	<form:hidden path="version" />		
	<form:hidden path="brotherhood" />
	<form:hidden path="draft" />
	<form:hidden path="ticker" />
	<form:hidden path="floats" />
	
	<acme:textbox code="procession.title" path="title" />	
	<acme:textarea code="procession.description" path="description" />
	<form:input type="text" path="moment" class="date" name="moment"
		  title="dd/MM/yyyy"  />
			<form:errors path="moment" cssClass="error" />
	
	
	
	<acme:submit code="procession.submit" name="save" /> 	
	<acme:cancel url="procession/brotherhood/list.do" code="procession.cancel"/>
		<jstl:if test="${procession.id!= 0}">	
	<acme:submit code="procession.delete" name="delete" />		
	</jstl:if>
	
	
</form:form>
