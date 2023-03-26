package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import beans.Attendance;

import java.sql.Date;
import java.sql.Time;

import dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MarkAttendance
 */
@WebServlet("/MarkAttendance")
public class MarkAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MarkAttendance() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String punchIn = request.getParameter("punchIn");
		String punchOut = request.getParameter("punchOut");
		
		ApplicationDao dao = new ApplicationDao();
		Time currentTime = Time.valueOf(LocalTime.now());
		//DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		//String time = formatTime.format(currentTime);
		
		Date currentDate = Date.valueOf(LocalDate.now());
		
		String username = request.getParameter("uname");
		System.out.println("Got Username:"+username);
		
		if(punchIn != null) {
			int rowsAffected = dao.markAttendance(username, currentTime, null, 0, currentDate);
			System.out.println("Data enetered in the table for the user:"+username);
			
			if(rowsAffected > 0) {
				request.setAttribute("punchIn", "Success");
				List<Attendance> records = dao.fetchAttendanceRecord(username);
				
				request.setAttribute("records", records);
				request.setAttribute("uname", username);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
			
		}
		
		else if(punchOut != null) {
			System.out.println("Punch Out time:"+currentTime);
		}
	}

}
