package third.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;

/**
 * Servlet implementation class bookAppointmentServlet
 */
@WebServlet("/addPatientServlet")
public class addPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("uname")== null )
        	response.sendRedirect("redirectionjsp.jsp");
		else {
			DBplay2 db = new DBplay2();
			Connection con = db.getCon();
			request.getRequestDispatcher("addPatient.jsp").include(request,response);
			int amka = (int) Integer.parseInt(request.getParameter("amka"));
			String fn = request.getParameter("fn");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			String date =  request.getParameter("date");
			String query = "INSERT INTO doctorappointment.patient VALUES (?,?,?,?,?)";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, amka);
				ps.setString(2, fn);
				ps.setDate(3, date_new(date));
				ps.setString(4, email);
				ps.setString(5, tel);
				int rs = ps.executeUpdate();
				if(rs==1) 
					response.getWriter().print("<table><tr><td>Patient added succesfully!/td></tr></table>");
				else 
					response.getWriter().print("<table><tr><td>Patient could not get added, please check patient's credentials and try again!</td></tr></table>");

			} catch (Exception e) {
				response.getWriter().print("<table><tr><td>Patient could not get added, please check patient's credentials and try again!</td></tr></table>");

			}
	
		}
	}
	
	private java.sql.Date date_new(String date) throws ParseException{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(date);
        java.sql.Date new_date = new java.sql.Date(parsed.getTime());
		return new_date;
	}
		

}
