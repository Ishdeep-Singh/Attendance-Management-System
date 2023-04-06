package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import beans.Attendance;
import beans.Employee;

public class ApplicationDao {
	private static Connection connection = DBConnection.getConnectionToDatabase();
	
	public boolean validateUser(String username, String password) {
		boolean isValidUser = false;
		try {

			String query = "Select * from users where username = ? and password = ?";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet set = statement.executeQuery();

			while(set.next()) {
				isValidUser = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return isValidUser;
	}

	public List<Attendance> fetchAttendanceRecord(String uname) {
		Attendance attendance = null;
		List<Attendance> attList = new ArrayList<>();


		String sql = "select * from attendance where username like '%"+uname+"%'";

		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			if(set.next() == false) {
				
					attendance = new Attendance();
					
					attendance.setCurrentDate("");
					attendance.setEntryTime("");
					attendance.setExitTime("");
					attendance.setPunchFlag("");
					attendance.setUsername("");
					attendance.setTotalHours(0);
					attList.add(attendance);
				
			}
			else
			while(set.next()) {
				attendance = new Attendance();
				attendance.setUsername(uname);
				attendance.setEntryTime(set.getString("entry_time"));
				attendance.setExitTime(set.getString("exit_time"));
				attendance.setTotalHours(set.getInt("total_hours"));
				attendance.setCurrentDate(set.getString("curr_date"));
				attendance.setPunchFlag(set.getString("punch_flag"));
				attList.add(attendance);
			}
			System.out.println("Attendance List from the DB:"+attList);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return attList;
	}

	public String registerUser(String username, String name, String password, int age, String gender, String address, String department, String email) {

		int rowsAffectedUser = 0;
		int rowsAffectedEmployees = 0;
		String insertQuery = null;
		PreparedStatement statement = null;
		
		String sql = "select username from users";

		try {
			Statement statement1 = connection.createStatement();
			ResultSet set = statement1.executeQuery(sql);
			while(set.next()) {
				if(set.getString("username").toString().equals(username)) {
					return "error,ID already exists. Try entering new one";
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		try {

			insertQuery = "insert into users values(?,?,?,?,?,?)";

			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, name);
			statement.setString(4, name);
			statement.setInt(5, age);
			statement.setString(6, "Coding");

			rowsAffectedUser = statement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Exception while entering data in users:"+e.getMessage());
			e.printStackTrace();
		}
		try {
			insertQuery = "insert into employees values(?,?,?,?,?,?,?)";

			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, username);
			statement.setString(2, name);
			statement.setInt(3, age);
			statement.setString(4, gender);
			statement.setString(5, address);
			statement.setString(6, department);
			statement.setString(7, email);

			rowsAffectedEmployees = statement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Exception while entering data in employees:"+e.getMessage());
			e.printStackTrace();
		}

		return ""+rowsAffectedUser+","+rowsAffectedEmployees;
	}

	public List<Employee> fetchEmployeesRecord() {
		Employee emp = null;
		List<Employee> empList = new ArrayList<>();


		String sql = "select * from employees";

		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			while(set.next()) {
				emp = new Employee();
				emp.setUsername(set.getString("username"));
				emp.setName(set.getString("name"));
				emp.setGender(set.getString("gender"));
				emp.setAge(set.getInt("age"));
				emp.setAddress(set.getString("address"));
				emp.setDepartment(set.getString("department"));
				emp.setEmail(set.getString("email"));

				empList.add(emp);
			}
			System.out.println("Employees List from the DB:"+empList);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return empList;
	}


	public int markAttendance(String username, Time currentTime, Time exitTime, int i, Date currentDate, String punchInFlag) {

		int rowsAffected = 0;
		String insertQuery = null;
		PreparedStatement statement = null;

		try {

			insertQuery = "insert into attendance values(?,?,?,?,?,?)";

			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, username);
			statement.setTime(2, currentTime);
			statement.setTime(3, exitTime);
			statement.setInt(4, i);
			statement.setDate(5, currentDate);
			statement.setString(6, punchInFlag);

			rowsAffected = statement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Exception while entering data in users:"+e.getMessage());
			e.printStackTrace();
		}


		return  rowsAffected;
	}
	
	public int punchOut(String username, Time punchOut, Date currentDate) {
		int rowsAffected = 0;
		
		String sql = "select entry_time from attendance where username = '"+username+"' and curr_date = '"+currentDate+"'order by curr_date desc";
		Time entryTime = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			
			while(set.next()) {
				entryTime = set.getTime("entry_time");
			}
			System.out.println("Entry time:"+entryTime);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		String updateQuery = null;
		PreparedStatement statement = null;

		try {

			updateQuery = "update attendance set exit_time = ? , punch_flag = ? , total_hours = ? where username = ? and curr_date = ? order by entry_time desc LIMIT 1";
			
			statement = connection.prepareStatement(updateQuery);
			
			statement.setTime(1, punchOut);
			statement.setString(2, "F");
			statement.setInt(3, (int)Math.abs(ChronoUnit.HOURS.between(punchOut.toLocalTime(), entryTime.toLocalTime())) );
			statement.setString(4, username);
			statement.setDate(5, currentDate);
			
			System.out.println("Punch Out:"+punchOut);
			
			rowsAffected = statement.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println("Exception while entering data in users:"+e.getMessage());
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	public void closeConnection() {
		DBConnection.closeConnection(connection);
	}
	
}
