<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${actionURI}" modelAttribute="administrator">

	<form:hidden path="id" />
	<form:hidden path="version" />	
	<form:hidden path="userAccount" />

	<form:label path="name" >
		<spring:message code="administrator.name" />: &nbsp;
	</form:label>
	<form:input path="name" placeholder="John" />
	<form:errors cssClass="error" path="name" />		
	<br />


	<form:label path="middleName">
		<spring:message code="administrator.middleName" />: &nbsp;
	</form:label>
	<form:input path="middleName" placeholder="Fitzgerald"/>
	<form:errors cssClass="error" path="middleName" />		
	<br />


	<form:label path="surname" >
		<spring:message code="administrator.surname" />: &nbsp;
	</form:label>
	<form:input path="surname" placeholder="Kennedy"/>
	<form:errors cssClass="error" path="surname" />		
	<br />


	<form:label path="address" >
		<spring:message code="administrator.address" />: &nbsp;
	</form:label>
	<form:input path="address" placeholder="17th Madison Square"/>
	<form:errors cssClass="error" path="address" />		
	<br />


	<form:label path="email" >
		<spring:message code="administrator.email" />: &nbsp;
	</form:label>
	<form:input path="email" placeholder="johnny@gmail.com" />
	<form:errors cssClass="error" path="email" />	
	<br />


	<form:label path="phone" >
		<spring:message code="administrator.phone" />: &nbsp;
	</form:label>
	<form:input path="phone" placeholder="+34 123 456 789"/>
	<form:errors cssClass="error" path="phone" />
	<br />


	<form:label path="photo" >
		<spring:message code="administrator.photo" />: &nbsp;
	</form:label>
	<form:input path="photo" placeholder="http://www.pinterest.com/my-photo.png"/>
	<form:errors cssClass="error" path="photo" />
	<br />

	<jstl:if test="${administrator.id == 0}">
		<form:label path="userAccount.username">
		<spring:message code="administrator.username" />: &nbsp;
		</form:label>
		<form:input path="userAccount.username" placeholder="user" />
		<form:errors cssClass="error" path="userAccount.username" />
		<br />


		<form:label path="userAccount.password">
			<spring:message code="administrator.password" />:
		</form:label>
		<form:password path="userAccount.password" placeholder="XXXXX" />
		<form:errors cssClass="error" path="userAccount.password" />
		<br />
		
		<%-- <jstl:set var="userAccount.authorities" value="Authority.CUSTOMER" /> --%>
		<form:hidden path="userAccount.authorities"/>
	
	</jstl:if>


<input type="submit" name="save" 
	
	value="<spring:message code="administrator.save" />" />&nbsp;&nbsp;

<input type="button" name="cancel" 
	onclick="javascript: window.location.replace('welcome/index.do')"
	value="<spring:message code="administrator.cancel" />" />

</form:form>