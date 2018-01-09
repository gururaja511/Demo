<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<style>
body {
    background-color:#4158dd;
	margin: 0px;
}

h1{
	font-family : sans-serif;
	margin : 18px;
}
header, footer {
    padding: 1px;
    color: #4158dd;
    background-color: white;
    text-align: left;
}
div{
	margin-top : 115px;
}
input[type=text]{
    width: 20%;
    padding: 12px 20px;
    margin: 12px 475px;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
	border-radius: 10px;
}

 input[type=password] {
    width: 20%;
    padding: 12px 20px;
    margin: -9px 475px;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
	border-radius: 10px;
}

button {
    background-color: white;
    color: black;
    padding: 12px 20px;
    margin: 8px 530px;
     border: 1px solid #ccc;
    cursor: pointer;
    width: 10%;
	border-radius: 10px;
}

img{
float:right;
margin-top:-81px;

}



</style>
<body>
<header>
   <h1>IQ_Automation</h1>
   <img src = "img/inctureimg.png">
</header>


  <div class="container">
    <form action="Login" method="post">
    <input type="text" placeholder="Enter Username" name="user" required>
     <br></br>
   
    <input type="password" placeholder="Enter Password" name="password" required>
      <br></br>  
    <button type="submit">Login</button>
	</form>
    
  </div>



</body>
</html>
