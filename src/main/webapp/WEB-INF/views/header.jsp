<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div id="header">
	<table class="standardTable">
		<tr class="header">
			<td>
				<a href="/spitter/home" class="head"><h1>Spitter</h1></a>
			</td>
			<td>
				<table class="standardTable">
					<tr>
						<td align="right">Welcome, <a href="<c:url value="/spitters/${loggedSpitter.username}"/>" class="userLink"><b><sec:authentication property="principal.username" /></b></a> | <a href="<c:url value="/j_spring_security_logout" />" class="signout"> Sign out</a></td>
					</tr>
					<tr>
						<td align="right">
							<sf:form action="/spitter/spitters/search" method="GET">
								<table>
									<tr >
										<td><input size="50" type="text" name="query" ></td>
										<td><input type="submit" value="Search" class="submit"/></td>
									</tr>
								</table>
							</sf:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>