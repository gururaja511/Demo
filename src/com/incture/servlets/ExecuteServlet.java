package com.incture.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incture.db.DatabaseUtility;
import com.incture.utility.TestNgCreation;

/**
 * Servlet implementation class ExecuteServlet
 */
@WebServlet("/Execute")
public class ExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		String path="cmd /c start D:\\GridImplementationRandD\\TestNgXml.bat";
		System.out.println(path);
		System.out.println("Project"+request.getParameter("project"));
		String[] modules=request.getParameterValues("module[]");
		
		for(String modu:modules){
			System.out.println(modu);
		}
				
				
		Runtime rn=Runtime.getRuntime();
		Process pr=rn.exec(path);
		/*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ExecutePage.jsp");
		dispatcher.forward(request, response); 
		//response.sendRedirect("HomePage.jsp");

		DatabaseUtility db=new DatabaseUtility();
		TestNgCreation testng=new TestNgCreation();
		testng.xmlDomModifierCustom("/opt/workspace/FreshDirect/testng.xml",request.getRemoteAddr(),db.getUserPort(request.getParameter("userName")));
		Process p=Runtime.getRuntime().exec("/opt/workspace/FreshDirect/TestNgBatFile.sh");
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
*/
		
		  response.setContentType("text/html");
		  request.setAttribute("data", "Testing");
		  response.getWriter().write("Started Execution ...."+request.getRemoteAddr());
		  response.getWriter().close();


	}

}
