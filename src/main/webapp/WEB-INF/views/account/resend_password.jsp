<div>
	<form action="/spitter/account/resendPassword" method="POST">
		<table id="innerTable">
			<tr>
				<td colspan="2" align="center"><h2>Password Recovery</h2></td>
			</tr>
			<tr>
				<% String errorMessage = (String) request.getAttribute("errorMessage");
				if(errorMessage != null) {%>
				<td colspan="2" align="center">
					<p class="error">${errorMessage }</p>
				</td>
				<%} %>
			</tr>
			<tr>
				<th align="right">Username:</th>
				<td align="left"><input type="text" name="username"></td>
			</tr>
			<tr>
				<th align="right">Email:</th>
				<td align="left"><input type="text" name="email"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit" class="submit"></td>
			</tr>
		</table>
	</form>
</div>