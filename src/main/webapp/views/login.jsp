<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

form {
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	width: 100%;
	padding: 20px;
	box-sizing: border-box;
}

.form-container {
	margin-bottom: 15px;
}

.form-container label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

.form-container input[type=text], .form-container input[type=password] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-container button {
	background-color: #4CAF50;
	color: white;
	padding: 15px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	width: 100%;
}

.form-container button:hover {
	background-color: #45a049;
}

.form-container .cancelbtn {
	background-color: #f44336;
}

.form-container .cancelbtn:hover {
	background-color: #da190b;
}

.form-container .psw {
	text-align: center;
}

.form-container .psw a {
	color: #2196F3;
	text-decoration: none;
}

.form-container .psw a:hover {
	text-decoration: underline;
}

.alert {
	color: #d9534f;
	background-color: #f2dede;
	border: 1px solid #d9534f;
	padding: 10px;
	border-radius: 4px;
	margin-bottom: 15px;
}
</style>
</head>
<body>
	<form action="/ltwebT4/login" method="post">
		<c:if test="${alert !=null}">
			<h3 class="alert alert danger">${alert}</h3>
		</c:if>
		<div class="form-container">
			<label for="uname"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="uname" required> <label
				for="psw"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="psw" required>

			<button type="submit">Login</button>

			<label> <input type="checkbox" checked="checked"
				name="remember"> Remember me
			</label>
		</div>

		<div class="form-container" style="padding: 15px;">
			<button type="button" class="cancelbtn">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form>
</body>
</html>