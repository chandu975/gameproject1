package com.admin;

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


@WebServlet("/adminaddtrain")
public class AdminAddTrain extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TrainDao trainDao = new TrainDao();

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			Train train = new Train();
			train.setTr_no(Long.parseLong(req.getParameter("trainno")));
			train.setTr_name(req.getParameter("trainname").toUpperCase());
			train.setFrom_stn(req.getParameter("fromstation").toUpperCase());
			train.setTo_stn(req.getParameter("tostation").toUpperCase());
			train.setSeats(Integer.parseInt(req.getParameter("available")));
			train.setFare(Double.parseDouble(req.getParameter("fare")));
			int message = trainDao.addTrain(train);
			if (message>0) {
				RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Train Added Successfully!</p1></div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Error in filling the train Detail</p1></div>");
			}
			
		} catch (Exception e) {
			pw.println("<div class='tab'><p1 class='menu'>failed....</p1></div>");
		}

	}

}
