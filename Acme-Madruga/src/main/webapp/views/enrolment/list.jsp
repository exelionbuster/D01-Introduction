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

	<display:table name="floats" id="row"
		requestURI="enrolment/brotherhood/list.do" pagesize="5"
		class="displaytag">

		<display:column property="momment" titleKey="enrolment.momment" />
		<display:column property="position" titleKey="enrolment.position" />		
		<display:column property="member.name" titleKey="enrolment.member" />				
		
		<security:authorize access="hasRole('brotherhood')">
		<acme:edit titleKey="enrolment.edit" code="enrolment.edit" url="enrolment/brotherhood/edit.do?Id=${id}" />			
		</security:authorize>

	</display:table>



</security:authorize>
