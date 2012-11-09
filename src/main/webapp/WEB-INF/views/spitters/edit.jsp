<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div>

	<sf:form action="edit" method="POST" modelAttribute="editSpitter"
		enctype="multipart/form-data">
		<table id="wideTable">
			<tr>
				<td colspan="2" align="center">
					<h2>Edit your profile</h2>
				</td>
			</tr>
			<tr>
				<th align="right"><label for="user_full_name">Full
						name:</label></th>
				<td align="left"><sf:input path="fullName" size="15"
						id="user_full_name" value="${editSpitter.fullName }" /></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="fullName" cssClass="error" /></td>
			</tr>
			<tr>
				<th align="right"><label for="file_upload">Browse file:</label></th>
				<td align="left"><sf:input type="file" path="file"
						id="file_upload" /></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="file" cssClass="error" /></td>
			</tr>
			<tr>
				<th align="right"><label for="remove_avatar">Remove
						profile picture?</label></th>
				<td align="left"><sf:checkbox path="removeAvatar"
						id="removeAvatar" /></td>
			</tr>
			<tr>
				<th align="right"><label for="user_password">Password:</label></th>
				<td align="left"><sf:password path="password" size="30"
						showPassword="true" id="user_password" /> <small>6
						characters or more </small></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<th align="right"><label for="user_email">Email
						Address:</label></th>
				<td align="left"><sf:input path="email" size="30"
						id="user_email" value="${editSpitter.email }" /></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<th align="right"><label for="user_description">Description:</label></th>
				<td align="left"><sf:input path="description" size="30"
						id="user_description" value="${editSpitter.description }"/></td>
			</tr>
			<tr>
				<td></td>
				<td><sf:errors path="description" cssClass="error" /></td>
			</tr>
			<tr>
				<th></th>
				<td align="left"><input name="commit" type="submit"
					value="Update" class="submit" /></td>
			</tr>
		</table>
	</sf:form>

</div>