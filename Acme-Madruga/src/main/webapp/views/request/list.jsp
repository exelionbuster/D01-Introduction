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

	<display:table name="requests" id="row"
		requestURI="request/member/list.do" pagesize="5"
		class="displaytag">
		
		<display:column property="procession" titleKey="request.procession" />
		<display:column property="status" titleKey="request.status" />
		<display:column property="rejectedReason" titleKey="request.rejectedReason" />		
		<display:column property="processionRow" titleKey="request.processionRow" />
		<display:column property="processionColumn" titleKey="request.processionColumn" />				
		
		<security:authorize access="hasRole('brotherhood')">
		<acme:edit titleKey="request.edit" code="request.edit" url="request/brotherhood/edit.do?Id=${id}" />
		</security:authorize>

	</display:table>
