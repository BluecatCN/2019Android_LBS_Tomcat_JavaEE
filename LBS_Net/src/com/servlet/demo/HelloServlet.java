package com.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("handle the Get() request!!!");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		out.println("<Strong>Hello Servlet!</strong><br>");
		out.println("Hijo de Puta!!!");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("handle the Post() request!!!");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		out.println("<Strong>Hello Servlet!</strong><br>");
		out.println("Hijo de Puta!!!");
	}

}
