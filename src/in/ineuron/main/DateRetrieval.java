package in.ineuron.main;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.uti.JdbcUtil;

public class DateRetrieval {

	public static void main(String[] args) {

		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		connection = JdbcUtil.getConnection();
		String getDate = "Select id, name, dob, city from teachers where id =?";

		try {
			if (connection != null)
				pstmt = connection.prepareStatement(getDate);
			if (pstmt != null) {

				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the id::");
				int id = sc.nextInt();

				pstmt.setInt(1, id);
			}

			if (pstmt != null)
				resultSet = pstmt.executeQuery();

			if (resultSet != null) {

				if (resultSet.next()) {

					int id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					java.sql.Date date = resultSet.getDate(3);
					String city = resultSet.getString(4);

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String fDate = sdf.format(date);

					System.out.println(id + "\t" + name + "\t" + fDate + "\t" + city);

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			JdbcUtil.close(connection, pstmt, resultSet);
		}

	}

}
