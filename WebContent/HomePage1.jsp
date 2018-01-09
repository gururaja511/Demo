<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

</script>
<style>
div.container {
	height: 100px;
	width: 100%;
	border: 1px solid gray;
}

header, footer {
	padding: 1em;
	color: white;
	background-color: blue;
	clear: left;
	text-align: center;
}

nav {
	float: left;
	max-width: 560px;
	margin: 0;
	padding: 1em;
}

nav ul {
	list-style-type: none;
	padding: 0;
}

nav ul a {
	text-decoration: none;
}

article {
	margin-left: 870px;
	border-left: 1px solid gray;
	padding: 1em;
	overflow: hidden;
}
</style>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	if(session.getAttribute("userName")==null)
	{
		response.sendRedirect("LoginN.jsp");
	}

%>

	<div class="container">

		<header>
		<h1>Hello ${userName} Welcome to Incture Automation Execution</h1>
				
		</header>

		<nav>
			<b>This is one time setup</b><br>
			<b>Please make sure all below file should be avaliable in same folder</b>
		
			<p>Click on below link's to download respective files & start <b>"node.bat"</b> file </P>
		
<p>
		<ul>
		
			<li><a href="Download?downloadName=SeleniumJarfile">Download SeleniumJarfile</a></li>
			<li><a href="Download?downloadName=Chromefile">Download Chromefile</a></li>
			<li><a href="Download?downloadName=cmdExecution&port="+db.getUserPort(userName)>Download Start Node files</a></li>
		</ul>
</p>


<br><br><br><br>


	<!-- Click <a href='Logout'>Click here to Logout </a> -->
		<div style='float: left;'><a href="HomePage.jsp">To Continue </a></div> 
		<div style='float: right;'><a href="Logout">Click here to Logout</a></div>
		</nav>

		<article>
		</body>
</html>