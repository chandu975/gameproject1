package com.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TrainDao;
import com.model.Train;

@SuppressWarnings("serial")
@WebServlet("/view")
public class UserViewLinkGet extends HttpServlet {
	private TrainDao trainDao = new TrainDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			String trainNo = req.getParameter("trainNo");
			Train train = trainDao.getTrainById(trainNo);
			if (train != null) {
				RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Selected Train Detail</p1></div>");
				pw.println("<div class='tab'>" + "<table>" + "<tr><td class='blue'>Train Name :</td><td>"
						+ train.getTr_name() + "</td></tr>" + "<tr><td class='blue'>Train Number :</td><td>"
						+ train.getTr_no() + "</td></tr>" + "<tr><td class='blue'>From Station :</td><td>"
						+ train.getFrom_stn() + "</td></tr>" + "<tr><td class='blue'>To Station :</td><td>"
						+ train.getTo_stn() + "</td></tr>" + "<tr><td class='blue'>Available Seats:</td><td>"
						+ train.getSeats() + "</td></tr>" + "<tr><td class='blue'>Fare (INR) :</td><td>"
						+ train.getFare() + " RS</td></tr>" + "</table>" + "</div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("SearchTrains.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Train No." + req.getParameter("trainnumber")
						+ " is Not Available !</p1></div>");
			}
		} catch (Exception e) {
			pw.println("<div class='tab'><p1 class='menu'>failed ....</p1></div>");		}

	}

}
