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


<display:table name="members" id="row"
	requestURI="member/brotherhood/list.do" pagesize="5" class="displaytag">

	<display:column property="name" titleKey="member.title" />
	<display:column property="middleName" titleKey="member.middleName" />
	<display:column property="surname" titleKey="member.surname" />
	<display:column property="email" titleKey="member.email" />
	<display:column property="photoURL" titleKey="member.photoURL" />
	<display:column property="phoneNumber" titleKey="member.phone" />

	<jstl:if test="">
	<acme:edit titleKey="member.edit" code="member.edit"
		url="member/edit.do?memberId=${row.id}" />
	</jstl:if>





</display:table>

