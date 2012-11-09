<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<div align="center">
	<h2>All users</h2>
	<table>
		<c:forEach items="${users }" var="spitter">
			<tr>
				<td>
					<table class="userEntity">
						<tr>
							<td width="50"><img
								src="<s:url value="/resources/avatars/${spitter.avatar }"/>">
							</td>
							<td width="300"><a
								href='<s:url value="/spitters/${spitter.name }"/>'>${spitter.name
									}</a> ${spitter.fullName }
								<form action="/spitter/admin/delete_user" method="POST">
									<input type="hidden" name="username" value="${spitter.name }">
									<input type="submit" value="Delete" class="submit">
								</form></td>
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>