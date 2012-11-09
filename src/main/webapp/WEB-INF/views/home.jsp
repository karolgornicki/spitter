<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="x.y.spitter.domain.Spitter"%>
<%@ page import="x.y.spitter.util.Tuple"%>

<div>
	<table id="wideTable">
		<tr>
			<sf:form action="spittle" method="POST" modelAttribute="spittle">
				<td align="right">Update your status: <sf:input path="text" size="10"
						id="message_text" /></td>
				<td align="right"><input name="commit" type="submit"
					value="Update" class="submit" /></td>
			</sf:form>
		</tr>
		<tr>
			<td align="right">
				<span id="alert"></span>
			</td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><sf:errors path="text" /></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">
				<table id="wideTable">
					<c:forEach items="${recentSpittles }" var="spittle">
						<tr>
							<td width="50"><img
								src="<s:url value="/resources/avatars/${spittle.avatar }"/>" width="48" height="48" />
							</td>
							<td>
								<table id="spittleTable">
									<tr>
										<td>
											<a href='<s:url value="/spitters/${spittle.username }"/>'>${spittle.username}</a>,
											<fmt:formatDate	value="${spittle.date}" pattern="MMM d, YYYY 'at' h:mm a " />
											<%
												Spitter loggedSpitter = (Spitter) request.getSession().getAttribute(
															"loggedSpitter");
													Tuple spittleItem = (Tuple) pageContext.getAttribute("spittle");

													String loggedUser = loggedSpitter.getUsername();
													String spittleAuthor = spittleItem.getUsername();

													if (loggedUser.equals(spittleAuthor)) {
											%>
											<form action="spittle/delete" method="POST">
												<input type="hidden" name="id" value="${spittle.id }">
												<input type="submit" value="Delete" class="submit">
												
											</form> <%
 	}
 %>
										</td>
									</tr>
									<tr>
										<td>
											<p class="spittleMessage"><c:out value="${spittle.text }" /></p>
										</td>
									</tr>
								</table>
							</td>
						
						</tr>
					</c:forEach>

				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table id="wideTable">
					<tr>
						<td align="left">
							<%
								String isPrevious = (String) request.getAttribute("isPrevious");
								String isNext = (String) request.getAttribute("isNext");

								if (isPrevious.equals("yes")) {
							%>
							<form action="/spitter/home" method="post">
								<input type="hidden" name="direction" value="previous">
								<input type="hidden" name="anchorStart" value="${anchorStart }">
								<input type="hidden" name="anchorEnd" value="${anchorEnd }">
								<input type="submit" value="Previous" class="submit">
							</form> <%
		}
	%>
						</td>
						<td align="right">
							<%
								if (isNext.equals("yes")) {
							%>
							<form action="/spitter/home" method="post">
								<input type="hidden" name="direction" value="next"> <input
									type="hidden" name="anchorStart" value="${anchorStart }">
								<input type="hidden" name="anchorEnd" value="${anchorEnd }">
								<input type="submit" value="Next" class="submit">
							</form> <%
		}
	%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		document.getElementById('message_text').focus();
	</script>
	<script type='text/javascript'>
		function textLength(value) {
			var maxLength = 140;
			if (value.length > maxLength)
				return false;
			return true;
		}
		var oldValue = '';
		var alert = document.getElementById('alert');
		document.getElementById('message_text').onkeyup = function() {
			if (!textLength(this.value)) {
				this.value = oldValue;
				alert.innerHTML = 'Text is too long!'
			} else {
				oldValue = this.value;
				alert.innerHTML = ''
			}
		}
	</script>
</div>


