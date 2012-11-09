<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div>
	<h1>Followers</h1>
	<table>
		<c:forEach items="${followers }" var="spitter">
			<tr>
				<td>
					<img src="<s:url value="/resources/avatars/${spitter.avatar }"/>" >
					<a href='<s:url value="/spitters/${spitter.name }"/>'>${spitter.name }</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>