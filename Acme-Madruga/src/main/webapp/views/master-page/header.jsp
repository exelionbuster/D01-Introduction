<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Madruga Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="administrator/create.do"><spring:message code="master.page.administrator.create" /></a></li>	
					<li><a href="administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
					<li><a href="configuration/administrator/edit.do"><spring:message code="master.page.administrator.configuration" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('BROTHERHOOD')">
			<li><a class="fNiv"><spring:message	code="master.page.brotherhood" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="procession/brotherhood/list.do"><spring:message code="master.page.brotherhood.procession" /></a></li>
					<li><a href="float/brotherhood/list.do"><spring:message code="master.page.brotherhood.float" /></a></li>
					<li><a href="enrolment/brotherhood/list.do"><spring:message code="master.page.brotherhood.enrolment" /></a></li>	
					<li><a href="member/brotherhood/list.do"><spring:message code="master.page.brotherhood.member" /></a></li>
					<li><a href="request/brotherhood/list.do"><spring:message code="master.page.brotherhood.request" /></a></li>		
					<li><a href="brotherhood/brotherhood/edit.do"><spring:message code="master.page.brotherhood.edit" /></a></li>							
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('MEMBER')">
			<li><a class="fNiv"><spring:message	code="master.page.member" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="request/member/list.do"><spring:message code="master.page.member.request" /></a></li>
					<li><a href="enrolment/member/list.do"><spring:message code="master.page.member.enrolment" /></a></li>	
					<li><a href="member/member/edit.do"><spring:message code="master.page.member.edit" /></a></li>							
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="member/create.do"><spring:message code="master.page.member.create" /></a></li>
			<li><a class="fNiv" href="brotherhood/create.do"><spring:message code="master.page.brotherhood.create" /></a></li>
			<li><a class="fNiv" href="brotherhood/list.do"><spring:message code="master.page.brotherhood.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.actor" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>						
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
					<li><a href="brotherhood/list.do"><spring:message code="master.page.brotherhood.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

