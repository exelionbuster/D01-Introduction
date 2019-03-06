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



<display:table name="positions" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column titleKey="position.name">
		<jstl:choose>
			<jstl:when test="${locale eq 'en'}">
				<jstl:out value="${row.name.get('EN')}" />
			</jstl:when>
			<jstl:when test="${locale eq 'es'}">
				<jstl:out value="${row.name.get('ES')}" />
			</jstl:when>
		</jstl:choose>
	</display:column>
	<acme:edit titleKey="position.edit" code="position.edit"
		url="position/administrator/edit.do?positionId=${row.id}" />


</display:table>

	<div>
	<a href="position/administrator/create.do"> 
	<spring:message	code="position.name" /></a>
	</div>




