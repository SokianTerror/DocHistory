package third.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Servlet implementation class addAppointmentServlet	
 */
@WebServlet("/addAppointmentServlet")
public class addAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);

        if(session.getAttribute("uname")== null){
        	response.sendRedirect("redirectionjsp.jsp");
	    }else { 
			DBplay2 db = new DBplay2();
	        Connection con = db.getCon();
	        String query = "INSERT INTO doctorappointment.appointment VALUES(?,?,?,?,?,?,?,?)";
	        int id = (int) session.getAttribute("id");
	        int new_id = last_id(con,db);
	        if (new_id==0) {
	        	new_id = 1000;
	        }else {
	        	new_id += 1;
	        }
	        try {
	        	String amka = request.getParameter("amka");
	            String date = request.getParameter("date");
				PreparedStatement ps = con.prepareStatement(query);
				ps.setDate(1, date_new(date));
				ps.setInt(2, Integer.parseInt(amka));
				ps.setInt(3, id);
				ps.setInt(4, new_id);
				ps.setString(5, request.getParameter("disease"));
				ps.setString(6, request.getParameter("cure"));
				ps.setString(7, request.getParameter("medex"));
				ps.setDate(8, date_new(request.getParameter("dod")));
				
				int rs = ps.executeUpdate();	
				if(rs==1) {
					request.getRequestDispatcher("addAppointment.jsp").include(request,response);
					response.getWriter().print("<table><tr><td>Appointment inserted succesfully!</td></tr></table>");
				}else {
					request.getRequestDispatcher("addAppointment.jsp").include(request,response);
					response.getWriter().print("<table><tr><td>Appointment could not insert, check values and try again!</td></tr></table>");
				}
			} catch (Exception e) {
				request.getRequestDispatcher("addAppointment.jsp").include(request,response);
				response.getWriter().print("<table><tr><td>Please, be sure that patient with this amka exists!</td></tr></table>");
			}
	    }

	}
	
	private java.sql.Date date_new(String date) throws ParseException{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(date);
        java.sql.Date new_date = new java.sql.Date(parsed.getTime());
		return new_date;
		
	}
	
	private int last_id(Connection con, DBplay2 db) {
		String query = "Select appointment_id from doctorappointment.appointment ORDER BY appointment_id DESC LIMIT 1;";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			int id;
			if(rs.next()) {
				id = rs.getInt(1);
			}else {
				id = 0;
			}
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}

}
