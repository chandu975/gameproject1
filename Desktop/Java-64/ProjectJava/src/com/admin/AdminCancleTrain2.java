package com.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cancletrainfwd")
public class AdminCancleTrain2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		RequestDispatcher rd = req.getRequestDispatcher("CancleTrain.html");
		rd.forward(req, res);

	}

}
