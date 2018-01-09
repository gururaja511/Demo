package com.incture.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incture.utility.DownloadUtility;


@WebServlet("/Download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String downloadType=request.getParameter("downloadName");
		
		
		String path;
		
		path="D:/opt/downloadsForWebapp/";
		
		
		if(downloadType.equals("SeleniumJarfile"))			
			DownloadUtility.downloadFile(path+"selenium-server-standalone-3.0.1.jar", response);
		else if(downloadType.equals("Chromefile"))
			DownloadUtility.downloadFile(path+"chromedriver.exe", response);
		else if(downloadType.equals("cmdExecution"))
			DownloadUtility.createBatFileAnddownload("node.bat",request.getParameter("port"), response);
		else if(downloadType.equals("TestDataFile"))
			DownloadUtility.downloadFile(path+"Test_Condition_Records.xlsx", response);
		else if(downloadType.equals("Report"))
			DownloadUtility.downloadFile(path+"Report.html", response);
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
