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
			while(set.next()) {
				attendance = new Attendance();
				attendance.setUsername(uname);
				attendance.setEntryTime(set.getString("entry_time"));
				attendance.setExitTime(set.getString("exit_time"));
				attendance.setTotalHours(set.getInt("total_hours"));
				attendance.setCurrentDate(set.getString("curr_date"));

				attList.add(attendance);

				//				System.out.println("Username:"+uname);
				//				System.out.println("entry_time:"+attendance.getEntryTime());
				//				System.out.println("exit_time:"+attendance.getExitTime());
				//				System.out.println("total_hours:"+attendance.getTotalHours());
				//				System.out.println("curr date:"+attendance.getCurrentDate());
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

				//				System.out.println(set.getString("username"));
				//				System.out.println(set.getString("name"));
				//				System.out.println(set.getString("gender"));
				//				System.out.println(set.getInt("age"));
				//				System.out.println(set.getString("address"));
				//				System.out.println(set.getString("department"));
				//				System.out.println(set.getString("email"));
			}
			System.out.println("Employees List from the DB:"+empList);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return empList;
	}


	public int markAttendance(String username, Time currentTime, Time exitTime, int i, Date currentDate) {

		int rowsAffected = 0;
		String insertQuery = null;
		PreparedStatement statement = null;

		try {

			insertQuery = "insert into attendance values(?,?,?,?,?)";

			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, username);
			statement.setTime(2, currentTime);
			statement.setTime(3, exitTime);
			statement.setInt(4, i);
			statement.setDate(5, currentDate);

			rowsAffected = statement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Exception while entering data in users:"+e.getMessage());
			e.printStackTrace();
		}


		return  rowsAffected;
	}
	
}
