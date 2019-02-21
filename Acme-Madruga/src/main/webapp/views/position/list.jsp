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

<security:authorize access="isAuthenticated()">

	<display:table name="positions" id="row"
		requestURI="position/administrator/list.do" pagesize="5"
		class="displaytag">

		<display:column property="name" titleKey="position.name" />		
		<security:authorize access="hasRole('ADMINISTRATOR')">
		<acme:edit titleKey="position.edit" code="position.edit" url="position/administrator/edit.do?Id=${id}" />			
		</security:authorize>

	</display:table>



</security:authorize>
