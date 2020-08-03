package com.servlet.demo;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
public class ServletDemo1 extends HttpServlet {
 
	
	private String transerFileName ;
	
	/**
	 * Constructor of the object.
	 */
	public ServletDemo1() {
		super();
	}
 
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
 
	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
 
	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
 
		// /response.setHeader("Content-Type",
		// "application/x-www-form-urlencoded; charset=GBK");
 
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");////���������Ҫ����Ȼ���ĵ��ļ�������ʾ����
		// �����ļ���Ŀ��������
		DiskFileItemFactory factory = new DiskFileItemFactory();
 
		// �����ļ��ϴ�·��
		//String upload = this.getServletContext().getRealPath("/upload/");
		
		String upload="C:/Program Files/apache-tomcat-8.5.40/webapps/LBS_Net/upload/";
		// ��ȡϵͳĬ�ϵ���ʱ�ļ�����·������·��ΪTomcat��Ŀ¼�µ�temp�ļ���
		// String temp = System.getProperty("java.io.tmpdir");
 
		// ���û�������СΪ 500M
		factory.setSizeThreshold(1024 * 1024 * 500);// //����������̫����ϴ�ʧ��
		// ������ʱ�ļ���Ϊtemp
		// factory.setRepository(new File(temp));
		factory.setRepository(new File(upload));
		// �ù���ʵ�����ϴ����,ServletFileUpload ���������ļ��ϴ�����
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
 
		// �����������List��
 
		List<FileItem> list;
		try {
			list = servletFileUpload.parseRequest(request);
 
			for (FileItem item : list) {
				String name = item.getFieldName();
				InputStream is = item.getInputStream();
				
			if (name.contains("file")) {
					try {
						InputStream input = item.getInputStream();
				
						String itemName = item.getName();
						String fileName = itemName.substring(
								itemName.lastIndexOf("\\") + 1,
								itemName.length());
 
						FileOutputStream output = new FileOutputStream(
								new File(
										"C:/Program Files/apache-tomcat-8.5.40/webapps/LBS_Net/upload/"
												+ fileName));
						byte[] buf = new byte[102400];
						int length = 0;
						while ((length = input.read(buf)) != -1) {
							output.write(buf, 0, length);
						}
						input.close();
						output.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.write("success");
					out.flush();
					out.close();
 
				}// / if (name.contains("file"))
			}// /for
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
 
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
 
}