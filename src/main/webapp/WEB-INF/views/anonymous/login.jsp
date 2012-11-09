<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div>
	<table id="innerTable">
		<tr>
			<td><spring:url var="authUrl"
					value="/static/j_spring_security_check" />
				<form method="post" class="signin" action="${authUrl}">
					<table class="body">
						<tr>
							<th align="right"><label for="username_or_email">Username: </label></th>
							<td align="left"><input id="username_or_email"
								name="j_username" type="text" /></td>
						</tr>
						<tr>
							<th align="right"><label for="password">Password: </label></th>
							<td align="left"><input id="password" name="j_password"
								type="password" />
						</tr>
						<tr>
							<td align="right"><small><a
									href="/spitter/account/resendPassword">Forgotten your password?</a></small></td>
							<td><input name="commit" type="submit" value="Sign In"
								class="submit" /></td>
						</tr>
						<tr height="35px"></tr>
						<tr>
							<td></td>
							<td>
								<a href="/spitter/registration">Don't have account yet? Sign up!</a>
							</td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>

	<script type="text/javascript">
		document.getElementById('username_or_email').focus();
	</script>
</div>