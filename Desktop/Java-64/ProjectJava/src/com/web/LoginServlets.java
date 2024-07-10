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

import com.dao.CustomerDao;
import com.model.Customer;

@WebServlet("/LoginServlets")
public class LoginServlets extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailId = request.getParameter("email");	
		String password = request.getParameter("password");	

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(50);
		session.setAttribute("email", emailId);
		session.setAttribute("password", password);
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<html>");
		
			CustomerDao custDao = new CustomerDao();
			Customer customer = custDao.getCustomer(emailId, password);
	
			session.setAttribute("customer", customer);
			
			if(customer!=null){
				RequestDispatcher rd = request.getRequestDispatcher("UserHome.html");
				rd.forward(request, response);
			}
			else{
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
