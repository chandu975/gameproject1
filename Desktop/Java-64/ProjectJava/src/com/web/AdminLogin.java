package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String uName = request.getParameter("uname");
		String pWord = request.getParameter("pword");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(50);
			session.setAttribute("uName", uName);
			session.setAttribute("pWord", pWord);
			if(uName.equalsIgnoreCase("ADMIN") && uName.equalsIgnoreCase("ADMIN")){
				RequestDispatcher rd = request.getRequestDispatcher("AdminHome.html");
				rd.forward(request, response);
			}else{
				out.print("<body bgcolor=yellow text=red>");
				out.print("<h3>Invalid Credentials</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("UserLogin.html");
				rd.include(request, response);

				out.print("</body>");
			}
		
		out.print("</html>");		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
