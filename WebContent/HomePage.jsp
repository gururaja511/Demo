<%@page import="java.util.ArrayList"%>
<%@page import="com.incture.db.DatabaseUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
<%	DatabaseUtility db=new DatabaseUtility();%>

$(function(){
 $(".upload").hide();
 });


$(document).ready(function(){
	
    $("#upload").click(function(){
        $(".upload").toggle();
    });
    var countries = [];
    $("#Module").change(function(){
    	countries=[];
    	 
         $.each($("#Module option:selected"), function(){ countries.push($(this).val()) });
         alert("You have selected the country - " + countries);
    	
    	
    	
    	var selectProj = $("#Project :selected").val();
    	var selectModule = $("#Module :selected").val();
    	$("#uploadPath").prop("value",selectProj+":"+selectModule);
    	//alert($("#file").prop("class"));
    	
    });
    
    
    
    $("#StartExecution").click(function(){
    	$.ajax({
        		
        		type:"POST",
        		url:"Execute",
        		data:{userName:$("#StartExecution").attr("userName"),project:$("#Project :selected").val(),module:countries},//module:$("#Module :selected").val()
        		success : function(response){
        		// alert(response);//ExecutionMessage
        		 $("#ExecutionMessage").html("<font color='green'>"+response+"</font>");
        		 $("#ExecutionMessage").show();
        		},
        		error : function(msg){
        			$("#ExecutionMessage").html("<font color='red'>"+response+"</font>");
        		}
        		
        	});
    	
    	
    	});	
   
    $("#uploadButton").click(function(){
    	
    	var path=$("#uploadPath").prop("value");
    	
    	$.ajax({

    		type: "POST",
            url: "UploadFile",
            data: {path:path},
              success: function(msg) {
                alert("File has been uploaded successfully");
                $("#message").html("<font color='green'>File has been uploaded successfully</font>");
            },
            error:function(msg) {
            	 $("#message").html("<font color='red'>Couldn't upload file</font>");
           }
    	
        });
    }); 	
  
     
   
});
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
		
		
	}else{
		
	
	%>

	<div class="container">

		<header>
		<h1>Hello ${userName} Welcome to Incture Automation Execution</h1>
		</header>

		<nav>



		<!-- <form  method="post">
			<br> Select Project : <select id="Project" name="Project">
				<%
	//DatabaseUtility db=new DatabaseUtility();
	
	ArrayList<String> listofProje= db.getUserProjectList("Gururajar");
	for(String list:listofProje)
	{
	%>
				<option value="<%=list%>"><%=list%></option>

				<%
	}
	
	%>
			</select> --></br>
			<br> Select Module : <select id="Module" name="Module" multiple="multiple" size="5">
				<option value=""></option>
				<%

	ArrayList<String> listofModule= db.getUserProjectList("Gururajar");
	for(String list:listofModule)
	{
		if(!list.equals("")){
	            %>
				<option value="<%=list.split(":")[1]%>"><%=list.split(":")[0]%></option>

				<%
		}
		
	}
	
	%>
			</select> </br></br></br></br>


			<b>Click on below button to start the execution</b><br><br><br><br>


			<button class="btn" type="submit" name="IpAddress" value="<%=request.getRemoteAddr()%>" id="StartExecution" userName="<%=session.getAttribute("userName").toString()%>">StartExecution</button>
		<!--  </form>-->

	<!-- Click <a href='Logout'>Click here to Logout </a> -->	
	<br><br><br><br><br>
		<div style='float: left;'><a href="HomePage1.jsp">To go back </a></div> 
		<div style='float: right;'><a target='_blank' href="http://192.168.5.36:9191/Report/Timesheet.html">Click here to get  Report</a></div><br><br>
		<div style='float: center;'><a href="Logout">Click here to Logout</a></div>
		</nav>
		<span id="ExecutionMessage" class="error" />
		</nav>

		<article>
		<h1>Please refer below links</h1>
		<p>
		<ul>
		
		<!--	<li><a href="Download?downloadName=SeleniumJarfile">Download SeleniumJarfile</a></li>
			<li><a href="Download?downloadName=Chromefile">Download Chromefile</a></li>
			<li><a href="Download?downloadName=cmdExecution&port="+db.getUserPort(userName)>Download Start Node files</a></li>-->
			<li><a href="Download?downloadName=TestDataFile">Download TestDataFile Format</a></li>
			<li><a id="upload" href="#">Upload Templete</a></li>
		</ul>

		</p>
		<div class="upload">
			Choose File to Upload in Server
			<form  id="upload-form" class="upload-box" method='post' enctype='multipart/form-data' action='UploadFile' >
				<input type='file' name='file' id='file' /> 
				<input type="hidden" id="uploadPath" name="uploadPath"/>
				<input type='submit' value='upload' id="uploadButton" />
				<span id="message" class="error" />
			</form>
		</div>

		</article>

		<footer>Copyright &copy; InctureAutomation.com</footer>

	</div>
	<%} %>

</body>
</html>

