<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page import="x.y.spitter.domain.Spitter"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>
	<s:url value="follow/{spitter_username}" var="follow_url">
		<s:param name="spitter_username" value="${spitter.username }" />
	</s:url>
	<s:url value="unfollow/{spitter_username}" var="unfollow_url">
		<s:param name="spitter_username" value="${spitter.username }" />
	</s:url>
	<table id="xTable">
		<tr>
			<td align="center" colspan="2">
				<h2>User profile</h2>
			</td>
		</tr>
		<tr>
			<td align="center" width="100"><img
				src="<s:url value="/resources/avatars/${spitter.avatar }"/>"></td>
			<td valign="top" width="500">
				<table id="wideTable">
					<tr >
						<th width="150" align="right">Username: </th>
						<td width="350" align="left">${spitter.username }</td>
					</tr>
					<tr>
						<th align="right">Real name: </th>
						<td align="left">${spitter.fullName }</td>
					</tr>
					<tr>
						<th valign="top" align="right">Comments:</th>
						<td align="left">${spitter.description }</td>
					</tr>
					<tr >
						<td align="right">
							<%
		Spitter spitterProfile = (Spitter) request.getAttribute("spitter");
		Spitter spitterSession = (Spitter) session.getAttribute("loggedSpitter");
		boolean followAlready = (Boolean) request.getAttribute("followAlready");

		if (!spitterProfile.getUsername().equals(spitterSession.getUsername())
				&& followAlready == true) {
	%> <a href="${unfollow_url}">Unfollow</a> <%
		}
		if (!spitterProfile.getUsername().equals(spitterSession.getUsername())
				&& followAlready == false) {
	%> <a href="${follow_url}">Follow</a> <%
		}
	%>
						</td>
						<td></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>