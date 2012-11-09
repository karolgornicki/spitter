<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div>
	<sf:form method="POST" modelAttribute="spitter">
		<table id="innerTable">
			<tr align="center">
				<td colspan="2"><h2>Create a new Spitter account</h2></td>
			</tr>
			<tr>
				<th align="right"><label for="user_full_name">Full
						name:</label></th>
				<td align="left"><sf:input path="fullName" size="15"
						id="user_full_name" /> </td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="fullName" cssClass="error"/></td>
			</tr>
			<tr>
				<th align="right"><label for="user_screen_name">Username: </label></th>
				<td align="left"><sf:input path="username" size="15"
						maxlength="15" id="user_screen_name" /> <small id="username_msg">No
						spaces, please.</small> </td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="username" cssClass="error"/></td>
			</tr>
			<tr>
				<th align="right"><label for="user_password">Password: </label></th>
				<td align="left"><sf:password path="password" size="30"
						showPassword="true" id="user_password" /> <small>6
						characters or more.</small> </td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<th align="right"><label for="user_email">Email
						Address:</label></th>
				<td align="left"><sf:input path="email" size="30"
						id="user_email" /> <small>In case you forget.</small> </td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="email" cssClass="error"/></td>
			</tr>
			<tr>
				<th></th>
				<td align="left"><input name="commit" type="submit"
					value="Create" class="submit" /></td>
			</tr>
		</table>
	</sf:form>
</div>