package com.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.rmi.server.Dispatcher;


public class Controller extends HttpServlet {
	
	private FlightDao dao = new FlightDao();
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		try {			
			switch (action) {
			
			case "/new":
				register(request, response);
				break;
			
			case "/book":
				bookTicket(request, response);
				break;
			
			case "/available":
				getAvailabity(request, response);
				break;
			
			case "/login":
				login(request, response);
				break;
				
			case "/logout":
				logout(request, response);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			
		}
		
	}
	
	private void bookTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		boolean isflight = dao.book_ticket(id);
		if (isflight) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/login_success.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Successfully reserved</font>");
            rd.include(request, response);
            
		}else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/login_success.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>No flight on this route</font>");
            rd.include(request, response);
		}
	}

	
private void getAvailabity(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		FlightModel flight = dao.getFlight(from, to);		
		
		if (flight != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/book_ticket.jsp");
			request.setAttribute("flight", flight);
			rd.forward(request, response);
		}else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/login_success.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>No flight on this route</font>");
            rd.include(request, response);
		}
		
		}

	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null ) {
			session.invalidate();
		}
		response.sendRedirect("index.jsp");
	}


	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName =request.getParameter("username");
		String password =request.getParameter("password");
		
		FlightDao dao = new FlightDao();
		try {
			boolean isUser =dao.login(userName,password);
			if(isUser) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(3*60);
				Cookie user = new Cookie("user", userName);
				response.addCookie(user);
				
				RequestDispatcher rqDispatcher = request.getRequestDispatcher("/WEB-INF/login_success.jsp");
				rqDispatcher.forward(request, response);
				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private void register(HttpServletRequest request, HttpServletResponse response) {
		
		String firstName =request.getParameter("firstname");
		String lastName =request.getParameter("lastname");
		String dob =request.getParameter("dob");
		String userName =request.getParameter("username");
		String password =request.getParameter("password");

		
		FlightDao dao = new FlightDao();
		try {
			dao.register(firstName,lastName,dob,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
