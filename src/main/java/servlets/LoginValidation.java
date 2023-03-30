package servlets;

import java.io.IOException;
import java.util.List;

import beans.Attendance;
import dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginValidation() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
		/*
		 * RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		 * dispatcher.forward(request, response);
		 */
		
		
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		
		ApplicationDao dao = new ApplicationDao();
		
		if(dao.validateUser(username, password)) {
			
			List<Attendance> records = dao.fetchAttendanceRecord(username);
			
			request.setAttribute("records", records);
			request.setAttribute("uname", username);
			request.setAttribute("punchFlag", records.get(records.size()-1).getPunchFlag());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			String errorMessage = "Invalid Credentials. Please login again";
			request.setAttribute("error", errorMessage);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		dao.closeConnection();
		
	}

}
