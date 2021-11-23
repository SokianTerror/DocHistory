package third.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import mainpackage.SQLException;
import third.DBplay2;
import third.energeies.Encrypt;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				PrintWriter out=response.getWriter();
		DBplay2 db = new DBplay2();   
		Connection con = db.getCon(); 
		HttpSession session = request.getSession(); 
		response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
		String uname = request.getParameter("uname");
		String pass = request.getParameter("password");
		Boolean flag = false;
		String query = "SELECT * FROM doctorappointment.doctor where username = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String salt = rs.getString("salt");
				pass = Encrypt.getHashMD5(pass, salt);
				if(pass.equals(rs.getString("hashedpassword"))) {  
					session.setAttribute("id", rs.getInt("doctoramka"));
					session.setAttribute("uname", rs.getString("username"));
					response.sendRedirect("DoctorIndex.jsp");
				}else flag = true;
				
			}else flag=true;
		}catch(Exception e) {
			request.getRequestDispatcher("login.jsp").include(request, response);
			out.print("<table><tr><td>Something went wrong, please try again later!</td></tr></table>");
		}
		if (flag==true) {
			request.getRequestDispatcher("login.jsp").include(request, response);
			out.print("<table><tr><td>Wrong username and/or password!</td></tr></table>");
		}
		
	
	}
		
}