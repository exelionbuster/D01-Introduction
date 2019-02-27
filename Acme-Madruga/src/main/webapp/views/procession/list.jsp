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

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="isAuthenticated()">

	<display:table name="processions" id="row"
		requestURI="${requestURI}" pagesize="5"
		class="displaytag">

		<display:column property="title" titleKey="procession.title" />
		<display:column property="ticker" titleKey="procession.ticker" />
		<display:column property="description" titleKey="procession.description" />
		<display:column property="moment" titleKey="procession.moment" />		
		<display:column property="floats" titleKey="procession.floats" />		
		
		
		<security:authorize access="hasRole('brotherhood')">
			<acme:edit titleKey="procession.edit" code="procession.edit" url="procession/brotherhood/edit.do?processionId=${id}" />
		</security:authorize>

	</display:table>



</security:authorize>