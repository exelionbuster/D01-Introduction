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

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table name="enrolments" id="row"
	requestURI="enrolment/brotherhood/list.do" pagesize="5"
	class="displaytag">


	<display:column property="member.name" titleKey="enrolment.member" />

	<display:column titleKey="enrolment.position">
	<jstl:choose>
	<jstl:when test="${locale eq 'en'}">	
	<jstl:out value="${row.position.name.get('EN')}" />
	</jstl:when>
	<jstl:when test="${locale eq 'es'}">	
	<jstl:out value="${row.position.name.get('ES')}" />					
	</jstl:when>
	</jstl:choose>		
	</display:column>

	<display:column property="moment" titleKey="enrolment.moment" />

	<display:column property="dropOutMoment"
		titleKey="enrolment.dropoutmoment" />


	<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column titleKey="enrolment.edit">
			<a href="enrolment/brotherhood/edit.do?enrolmentId=${row.id}"> <spring:message
					code="enrolment.edit.select" />
			</a>
		</display:column>
		<display:column>
			<a href="enrolment/brotherhood/drop.do?enrolmentId=${row.id}"> <spring:message
					code="enrolment.remove" />
			</a>
		</display:column>
	</security:authorize>

</display:table>




