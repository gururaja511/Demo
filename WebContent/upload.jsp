<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Asynchronous file Upload in Java Web Application</title>
<script src="jquery-3.2.1.js"></script>
<script src="jquery.ajaxfileupload.js">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</script>
<script lang="Javascript">
$(document).ready(function() {
  $('input[type="file"]').ajaxfileupload({
  'action' : 'UploadFile',
  valid_extensions : ['xml','xlsx','md','csv'],
  'onComplete' : function(response) {
      $('#upload').hide();
      $('#message').show();
     /*                    
      var statusVal = JSON.stringify(response.status);

     if(statusVal == "false")
     {
     $("#message").html("<font color='red'>"+JSON.stringify(response.message)+"</font>");
     }  
     if(statusVal == "true")
     { */
    	
     $("#message").html("<font color='green'>"+JSON.stringify(response.message)+"</font>");
    /*  }    */               
},
'onStart' : function() {
        $('#upload').show();
        $('#message').hide();
}
});
});
</script>
<style type="text/css">
.centered {
	width: 100%;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
</head>
<body>
	<form>
		<div class="centered">
			<h4>AJAX Style File upload in Java Web Application</h4>
			<input type="file" name="file" /><br />
			<div id="upload" style="display: none;">Uploading..</div>
			<div id="message"></div>
		</div>
	</form>
</body>
</html>