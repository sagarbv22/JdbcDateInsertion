package in.ineuron.main;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.uti.JdbcUtil;



public class DateInsertion {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		int row = 0;

		connection = JdbcUtil.getConnection();
		try {
			if (connection != null)
				pstmt = connection.prepareStatement("Insert into teachers(name, dob, city) values(?,?,?)");

			if (pstmt != null) {
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the name::");
				String name = sc.next();
				System.out.print("Enter DOB yyyy-MM-dd::");
				String date = sc.next();
				System.out.println("Enter the City::");
				String city = sc.next();

				pstmt.setString(1, name);

				java.sql.Date sDate = java.sql.Date.valueOf(date);

				pstmt.setDate(2, sDate);
				pstmt.setString(3, name);

				row = pstmt.executeUpdate();
				sc.close();
			}
			
			if(row>0)
				System.out.println("Done");
			else
				
				System.out.println("Failed");
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
		
		JdbcUtil.close(connection, pstmt, null);

	}

}
