package third.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;
import third.energeies.Encrypt;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DBplay2 db = new DBplay2();
		Connection con = db.getCon();
		String username = request.getParameter("username");
		Boolean flag = false;
		try {
			SecureRandom random = new SecureRandom();
    		byte bytes[]= new byte[20];
    		random.nextBytes(bytes);
			String salt = random.toString();
			String pass = Encrypt.getHashMD5(request.getParameter("password"), salt);
			int amka = (int) Integer.parseInt(request.getParameter("amka"));
			if(!exists(con,amka)) {
				String query = "INSERT INTO doctorappointment.doctor VALUES (?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,amka);
				ps.setString(2, username);
				ps.setString(3, pass);
				ps.setString(4, salt);
				int rs = ps.executeUpdate();
				if(rs==1) {
					HttpSession session = request.getSession();
					session.setAttribute("uname", username);
					session.setAttribute("id", amka);
					response.sendRedirect("DoctorIndex.jsp");
				} else {
					request.getRequestDispatcher("Register.jsp").include(request, response);
					response.getWriter().print("<table><tr><td>Account could not be created, please check again the values and try again!</td></tr></table>");
				}
			}else {
				request.getRequestDispatcher("Register.jsp").include(request, response);
				response.getWriter().print("<table><tr><td>A doctor with this amka already exists, please check again your amka!</td></tr></table>");
			}
		} catch (SQLException e) {
			System.out.println(e);
			request.getRequestDispatcher("Register.jsp").include(request, response);
			response.getWriter().print("<table><tr><td>A doctor with this username already exists!</td></tr></table>");		}

		
	}
	
	
	public Boolean exists (Connection con, int amka) {
		String query = "SELECT * FROM doctorappointment.doctor where doctoramka = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, amka);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) return true;
			else return false;
		} catch (SQLException e) {
			
			return true;
		}
	}

}
