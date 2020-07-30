package fr.epita.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPG {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.137.50:10532/joins-exercises", "postgres","postgres");
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM table_a");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("Value"));
		}
		
		connection.close();
	}
	
}
