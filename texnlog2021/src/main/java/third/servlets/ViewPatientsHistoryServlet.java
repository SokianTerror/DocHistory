package third.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;


@WebServlet("/viewPatientsHistoryServlet")
public class ViewPatientsHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	HttpSession session = request.getSession(false);				
        if(session.getAttribute("uname") == null) {
        	response.sendRedirect("redirectionjsp.jsp");
        }else {
			DBplay2 db = new DBplay2();
			Connection con = db.getCon();
			int id = Integer.parseInt(request.getParameter("id")); 
			
			if(exists(con,id)) {
				String query = "select appointment.*, doctor.username from doctorappointment.appointment,doctorappointment.doctor"
						+ " where patient_patientamka = ? and doctor.doctoramka = appointment.doctor_doctoramka";
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, id);
					ResultSet resultSet = ps.executeQuery();
					response.setHeader("Cache-Control","no-cache");
					response.setHeader("Cache-Control","no-store"); 
					response.setHeader("Pragma","no-cache");
					response.setDateHeader ("Expires", 0);
					if(!resultSet.next()) { 
						request.getRequestDispatcher("DoctorIndex.jsp").include(request, response); 
						response.getWriter().print("<table><tr><td>No data for this patient!</td></tr></table>");
						 
					}
					else {
						request.getRequestDispatcher("viewPatientHistory.jsp").include(request, response); 
						int i =0;
						do { 
							if( i == 0) response.getWriter().print("<table> <tr> <th>Appointment Date</th> <th>Doctor</th> <th>Disease</th> <th> Cure</th><th> Medical Examination</th><th>Disease Date</th></tr>");
							i+=1;
						    String date =(String) resultSet.getString("date");
						    String doc_username =(String) resultSet.getString("username");
						    String disease =(String) resultSet.getString("disease");
						    String cure =(String) resultSet.getString("cure");
						    String medex =(String) resultSet.getString("medex");
						    String dis_date =(String) resultSet.getString("dateofdisease");
						    response.getWriter().print("<tr> <td>"+date+"</td> <td>"+doc_username+"</td>  <td>"+disease+"</td> <td>"+cure +"</td>"
						    		+"<td>"+medex+"</td><td>"+dis_date+"</td> </tr>");
				
						}while(resultSet.next());
						 response.getWriter().print("</table>");
					}
				}catch(Exception e) {
					System.out.println(e);
					request.getRequestDispatcher("DoctorIndex.jsp").include(request, response); 
					response.getWriter().print("An error has occured please try again later!");
				}
			}else {
				request.getRequestDispatcher("DoctorIndex.jsp").include(request, response); 
				response.getWriter().print("<table><tr><td>Patient with this amka does not exist!</td></tr></table>");}
        }
    }
	public Boolean exists (Connection con, int amka) {
		String query = "SELECT * FROM doctorappointment.patient where patientamka = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, amka);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) return true;
			else return false;
		} catch (SQLException e) {
			return false;
		}
	}
		
		
	}

