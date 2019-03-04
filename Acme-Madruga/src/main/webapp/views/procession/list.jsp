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



<display:table name="processions" id="row"
	requestURI="procession/brotherhood/list.do" pagesize="5"
	class="displaytag">

	<display:column property="title" titleKey="procession.title" />
	<display:column property="ticker" titleKey="procession.ticker" />
	<display:column property="description"
		titleKey="procession.description" />
	<display:column property="moment" titleKey="procession.moment" format="{0,date,dd/MM/yyyy}" />




	<display:column titleKey="procession.floats">
		<jstl:choose>
			<jstl:when test="${not empty row.floats}">
				<jstl:forEach var="flo" items="${row.floats}">
					<a href="float/brotherhood/edit.do?floatId=${flo.id}"><jstl:out
							value="${flo.title}" /></a>
				</jstl:forEach>
			</jstl:when>
			<jstl:when test="${empty row.floats}">
				<spring:message code="procession.empty" />
			</jstl:when>
		</jstl:choose>
	</display:column>

	<security:authorize access="hasRole('BROTHERHOOD')">
		<acme:edit titleKey="procession.edit" code="procession.edit"
			url="procession/brotherhood/edit.do?processionId=${row.id}" />
	</security:authorize>

</display:table>

<div>

	<a href="procession/brotherhood/create.do"> <spring:message
			code="procession.create" /></a>
</div>



