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

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



	<display:table name="floats" id="row"
		requestURI="float/brotherhood/list.do" pagesize="5"
		class="displaytag">
		
		<display:column property="title" titleKey="float.title" />
		<display:column property="description" titleKey="float.description" />
		<display:column property="pictures" titleKey="float.pictures" />		
		<security:authorize access="hasRole('BROTHERHOOD')">
		<acme:edit titleKey="float.edit" code="float.edit" url="float/brotherhood/edit.do?floatId=${row.id}" />
		</security:authorize>
		
		
	</display:table>
	
	<div>
	<a href="float/brotherhood/create.do"> 
	<spring:message	code="float.create" /></a>
	</div>





