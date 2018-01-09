package com.incture.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile1")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String UPLOAD_DIRECTORY = "D:/";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out=response.getWriter();
		
		System.out.println("i am calling UploadFile ");
		
		      
		System.out.println("path"+request.getParameter("path"));
		
		 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 System.out.println("isMultipart"+isMultipart);
	        // process only if its multipart content
	        if (isMultipart) {
	        	System.out.println("i am calling UploadFile in is multipart ");
	                // Create a factory for disk-based file items
	                FileItemFactory factory = new DiskFileItemFactory();

	                // Create a new file upload handler
	                ServletFileUpload upload = new ServletFileUpload(factory);
	                try {
	                    // Parse the request
	                    List<FileItem> multiparts = upload.parseRequest(request);

	                   for (FileItem item : multiparts) {
	                   if (!item.isFormField()) {
	                	
	                   String name = new File(item.getName()).getName();
	                   System.out.println("in if name "+name);
	                   item.write(new File(UPLOAD_DIRECTORY+ File.separator + name));
	                   }
	                   else{
	                	   String fieldname = item.getFieldName();
	                       String fieldvalue = item.getString();
	                       System.out.println("fieldname--"+fieldname +"fieldvalue--"+fieldvalue);
	                   }
	                }
	                   out.println("<!DOCTYPE html>");
	           		out.println("<html><head>");
	           		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	           		out.println("<body>");
	           		out.println("<P>Your file has been uploaded!</P>");
	        		out.println("</body>");
	        		out.println("</html>");

	                // File uploaded successfully
	                request.setAttribute("message", "Your file has been uploaded!");
	                } 
	                catch (Exception e) 
	                {
	                 request.setAttribute("message", "File Upload Failed due to " + e);
	                }
	        } else 
	        {
	        request.setAttribute("message", "This Servlet only handles file upload request");
	        }
	       // request.getRequestDispatcher("LoginServlet").forward(request, response);
		
	}
}
