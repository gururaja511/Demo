package com.incture.utility;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtility {


	public static void downloadFile(String fileName,HttpServletResponse response) throws IOException{

		ServletOutputStream sos;

		//fileName="C:/Users/gururaja.reddy/workspace_WebApp/InctureAutomationDev/WebContent/Login.jsp";  //.docx or .txt

		//System.out.println(fileName);

		File f = new File(fileName);

		FileInputStream istr = new FileInputStream(f);

		BufferedInputStream bstr = new BufferedInputStream( istr );

		int size = (int) f.length(); // get the file size (in bytes)

		byte[] data = new byte[size];

		bstr.read( data, 0, size ); // read into byte array

		bstr.close();

		response.setContentType("application/octet-stream");

		response.setHeader("Content-Disposition","attachment; filename="+fileName.split("/")[fileName.split("/").length-1]+"");
		//	response.setHeader("Content-Disposition", "test.jsp");
		sos = response.getOutputStream();

		sos.write(data);

		sos.flush();

		sos.close();
	}


	public static void createBatFileAnddownload(String fileName,String port,HttpServletResponse response) throws IOException{

		ServletOutputStream sos;

		File f = new File(fileName);

		StringBuffer cmd=new StringBuffer("cd /d  lib");
		cmd.append("\n");
		cmd.append("java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone-3.0.1.jar -port "+port+" -role node -hub http://192.168.5.36:4443/grid/register/  -browser \"browserName=chrome, version=ANY, maxInstances=5, platform=WINDOWS\" ");
		cmd.append("\n");
		cmd.append("pause");

		response.setContentType("application/octet-stream");

		response.setHeader("Content-Disposition","attachment; filename="+fileName+"");
		//response.setHeader("Content-Disposition", "test.jsp");
		sos = response.getOutputStream();
		System.out.println(cmd);
		sos.write(cmd.toString().getBytes());

		sos.flush();

		sos.close();
	}
}
