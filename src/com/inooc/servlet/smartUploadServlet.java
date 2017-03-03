/**
* 2015-7-25  
* smartUploadServlet.java 
* author:JunYu Wu 
*/
package com.inooc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

/**
 * @author asus1
 *
 */
public class smartUploadServlet extends HttpServlet {

	
	public smartUploadServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String filePath=getServletContext().getRealPath("/")+"images";
		File file=new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
		SmartUpload su =new SmartUpload();
		su.initialize(getServletConfig(), request, response);
		su.setMaxFileSize(1024*10*10);//设置文件大小
		su.setTotalMaxFileSize(1024*10*10);
		su.setAllowedFilesList("txt,jpg,gif");//允许上传
		try {
			su.setDeniedFilesList("rar,jsp");//不允许上传
			su.upload();
			int count=su.save(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", "success");
		request.getRequestDispatcher("jsp/02.jsp").forward(request, response);
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
