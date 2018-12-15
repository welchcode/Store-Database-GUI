import java.sql.*;

/*
 * Database class that connects GUI to local PostgreSQL database
 * 
 * */
public class DB {
	//Main Database Method
	public static void connect() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/store_db";			// URL used to connect 
		Connection conn = DriverManager.getConnection(url,null,null);		//getConnection used for connecting program to local DB
		if(conn != null)
			System.out.println("Database connected successfully");		//if the connection is successful, print this
	}
	//This method, in the future will allow updates to happen to the database -- Its current state is for testing purposes
	public static void updateCompany() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/store_db";
		Statement stmt = null;
		Connection conn = DriverManager.getConnection(url,null,null);		//reconnecting the database to the program-- this will be rewritten as its own method later
		if(conn != null)
			System.out.println("Database connected successfully");
		conn.setAutoCommit(false);
		
		stmt = conn.createStatement();
		String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + // current test statement
					"VALUES (3,'Paul',32,'California',20000.00);";
		stmt.executeUpdate(sql);
		System.out.println("Update successful");
		stmt.close();
		conn.commit();
		conn.close();
	}
}
