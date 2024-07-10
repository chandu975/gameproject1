package com.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/adminsearchtrainfwd")
public class AdminSearchTrain2 extends HttpServlet {

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("AdminSearchTrain.html");
		rd.forward(request, response);

	}

}
