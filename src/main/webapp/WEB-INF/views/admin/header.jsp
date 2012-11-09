<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div id="header">
	<table id="mainTable">
		<tr class="header">
			<td align="left">
				<a href="/spitter/admin/main" class="head"><h1>Spitter - Admin panel</h1></a>
			</td>
			<td align="right">
				Welcome, <a href="<c:url value="/spitters/${loggedSpitter.username}"/>" class="userLink"><b><sec:authentication property="principal.username" /></b></a> | <a href="<c:url value="/j_spring_security_logout" />" class="signout"> Sign out</a>
			</td>
		</tr>
	</table>
</div>