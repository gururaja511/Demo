<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.Comparator"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="ReportsNew.jsp" method="POST">
		<%
			System.out.println(request.getParameter("path"));
			//String file = application.getRealPath("/WEB-INF/lib");
			String pathParam = request.getParameter("path");
			String file = application.getRealPath("/" + pathParam);
			File f = new File(file);
			//String[] fileNames = f.list();

			File[] fileObjects = f.listFiles();
			Arrays.sort(fileObjects, new Comparator<File>() {
				public int compare(File f1, File f2) {
					return Long.compare(f1.lastModified(), f2.lastModified());
				}
			});
			int exp = 0;
			if (fileObjects.length > 10) {
				exp = 11;
			} else {
				exp = fileObjects.length + 1;
			}
			System.out.println("exptet  " + exp);
			for (int i = fileObjects.length - 1; i > (fileObjects.length - exp); i--) {
				System.out.println("i " + i);
				if (fileObjects[i].isDirectory() && !(fileObjects[i].getName().equalsIgnoreCase("images"))) {
					//String fname = file + fileNames[i];
					/*System.out.println(fileNames[i]+"  "+fname);*/
		%><a
			HREF="<%="ReportsNew.jsp?path=" + pathParam + "/" + fileObjects[i].getName()%>"><%=fileObjects[i].getName()%></a>
		<br>
		<%
			} else if (fileObjects[i].isFile()) {
		%><a
			HREF="<%=request.getContextPath() + "/" + pathParam + "/" + fileObjects[i].getName()%>"><%=fileObjects[i].getName()%></a>
		<br>
		<%
			}
			}
		%>
	</form>
</body>
</html>