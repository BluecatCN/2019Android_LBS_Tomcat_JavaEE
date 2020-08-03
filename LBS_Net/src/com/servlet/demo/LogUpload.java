package com.servlet.demo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogUpload extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}


    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws IOException  
    {  
        InputStream is = request.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        String result = "";
        try {
            result = saveFile(dis);
        } catch (Exception e) {
            e.printStackTrace();
            result = "uploaderror";
        }

        request.getSession().invalidate();
        response.setContentType("text/plain");
        ObjectOutputStream dos = new ObjectOutputStream(
                    response.getOutputStream());
        dos.writeObject(result);
        dos.flush();
        dos.close();
        dis.close();
        is.close();
    }

/**
     * ±£´æÎÄ¼þ
     * @param dis
     * @return
     */
    private String saveFile(DataInputStream dis) {
        String fileurl = "C:/Program Files/apache-tomcat-8.5.40/webapps/ROOT/LBS_data/lbs10_gaode2.txt";
        File file = new File(fileurl);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fps = null;
        try {
            fps = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length = -1;

        try {
            while ((length = dis.read(buffer)) != -1) {
                fps.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fps.flush();
            fps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Merde Success!!!";
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


}
