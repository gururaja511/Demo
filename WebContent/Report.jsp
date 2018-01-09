<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		System.out.println(request.getParameter("path"));
		//String file = application.getRealPath("/WEB-INF/lib");
		String pathParam = request.getParameter("path");
		String file = application.getRealPath("/" + pathParam);
		File f = new File(file);
		String[] fileNames = f.list();
		File[] fileObjects = f.listFiles();
		for (int i = 0; i < fileObjects.length; i++) {
			if (fileObjects[i].isDirectory()) {
				String fname = file + fileNames[i];
				/*System.out.println(fileNames[i]+"  "+fname);*/
	%><a HREF="<%="Report.jsp?path=" + pathParam + "/" + fileNames[i]%>"><%=fileNames[i]%></a>
	<br>
	<%
		} else if (fileObjects[i].isFile()) {
				System.out.println(request.getContextPath() + pathParam + "/" + fileNames[i]);
	%><a
		HREF="<%=request.getContextPath() +"/"+ pathParam + "/" + fileNames[i]%>"><%=fileNames[i]%></a>
	<br>
	<%
		}
		}
	%>
</body>
</html>