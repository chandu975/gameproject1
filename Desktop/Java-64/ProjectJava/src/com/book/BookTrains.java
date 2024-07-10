package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookingDao;
import com.dao.HistoryDao;
import com.dao.TrainDao;
import com.model.Booking;
import com.model.Train;

@SuppressWarnings("serial")
@WebServlet("/booktrains")
public class BookTrains extends HttpServlet {

	private TrainDao trainDao = new TrainDao();
	private BookingDao bookingService = new BookingDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		HttpSession session = req.getSession();

		String emailId = (String) session.getAttribute("email");
		if (emailId == null) {
			pw.println("<div class='tab'><p class='menu red'>Please login first!</p></div>");
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
		rd.include(req, res);

		ServletContext sct = req.getServletContext();

		try {
			int seat = (int) sct.getAttribute("seats");
			String trainNo = (String) sct.getAttribute("trainnumber");
			String journeyDate = (String) sct.getAttribute("journeydate");
			String seatClass = (String) sct.getAttribute("class");

			// Ensure the journeyDate is in yyyy-MM-dd format
			DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(journeyDate, inputFormat);

			Train train = trainDao.getTrainById(trainNo);

			if (train != null) {
				int avail = train.getSeats();
				if (seat > avail) {
					pw.println("<div class='tab'><p class='menu red'>Only " + avail + " seats are available in this train!</p></div>");
				} else {
					avail -= seat;
					train.setSeats(avail);
					int responseCode = trainDao.updateTrain(train);

					if (responseCode > 0) {
						double totalAmount = train.getFare() * seat;
						HistoryDao bookingDetails = new HistoryDao();
						bookingDetails.setAmount(totalAmount);
						bookingDetails.setFrom_stn(train.getFrom_stn());
						bookingDetails.setTo_stn(train.getTo_stn());
						bookingDetails.setTr_no(trainNo);
						bookingDetails.setSeats(seat);
						bookingDetails.setMailId(emailId);
						bookingDetails.setDate(date.toString()); // Ensure date is in yyyy-MM-dd format

						HistoryDao transaction = bookingService.createHistory(bookingDetails);
						pw.println("<div class='tab'><p class='menu green'>" + seat + " seats booked successfully!<br/> Your Transaction Id is: " + transaction.getTransId() + "</p></div>");
						pw.println("<div class='tab'><p class='menu'><table>"
								+ "<tr><td>PNR No: </td><td colspan='3' style='color:blue;'>" + transaction.getTransId() + "</td></tr>"
								+ "<tr><td>Train Name: </td><td>" + train.getTr_name() + "</td><td>Train No: </td><td>" + transaction.getTr_no() + "</td></tr>"
								+ "<tr><td>Booked From: </td><td>" + transaction.getFrom_stn() + "</td><td>To Station: </td><td>" + transaction.getTo_stn() + "</td></tr>"
								+ "<tr><td>Date Of Journey:</td><td>" + transaction.getDate() + "</td><td>Time(HH:MM):</td><td>11:23</td></tr>"
								+ "<tr><td>Passengers: </td><td>" + transaction.getSeats() + "</td><td>Class: </td><td>" + seatClass + "</td></tr>"
								+ "<tr><td>Booking Status: </td><td style='color:green;'>CNF/S10/35</td><td>Amount Paid:</td><td>&#8377; " + transaction.getAmount() + "</td></tr>"
								+ "</table></p></div>");
					} else {
						pw.println("<div class='tab'><p class='menu red'>Transaction Declined. Try Again!</p></div>");
					}
				}
			} else {
				pw.println("<div class='tab'><p class='menu'>Invalid Train Number!</p></div>");
			}

		} catch (Exception e) {
			pw.println("<div class='tab'><p class='menu'>Failed....</p></div>");
		} finally {
			sct.removeAttribute("seats");
			sct.removeAttribute("trainnumber");
			sct.removeAttribute("journeydate");
			sct.removeAttribute("class");
		}
	}
}
