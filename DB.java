import java.sql.*;

/*
 * Database class that connects GUI to local PostgreSQL database
 * 
 * */
public class DB {
	//declaration of private vars
	private static String url = "jdbc:postgresql://localhost:5432/store_db";		// URL used to connect 
	private static Connection conn;
	private static Statement stmt;
	
	//Main Database Method
	public static void connect() throws SQLException {
		
		conn = DriverManager.getConnection(url,null,null);		//getConnection used for connecting program to local DB
		if(conn != null)
			System.out.println("Database connected successfully");		//if the connection is successful, print this
	}
	//This method, in the future will allow updates to happen to the database -- Its current state is for testing purposes
	public static void addItem(int id, String name, double price, int quantity) throws SQLException {		
		Connection conn = DriverManager.getConnection(url,null,null);		//reconnecting the database to the program-- this will be rewritten as its own method later
		if(conn != null)
			System.out.println("Database connected successfully");
		conn.setAutoCommit(false);
		
		stmt = conn.createStatement();
		String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + // current test statement
					"VALUES "+"("+id+","+"'"+name+"',"+price+","+quantity+");";
		stmt.executeUpdate(sql);
		System.out.println("Update successful");
		stmt.close();
		conn.commit();
		conn.close();
	}
	public static void addEmployee(String id, String firstName, String lastName,  String age, String streetAddress, String state, String salary) throws SQLException {
		stmt = conn.createStatement();
		String sql = "INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,AGE,ADDRESS,STATE,SALARY) " + // current test statement
				"VALUES "+"('"+id+"',"+"'"+firstName+"','"+lastName+"','"+age+"','"+streetAddress+"','"+state+"','"+salary+"')";		
		stmt.executeUpdate(sql);
		System.out.println("Update successful");
	}
}
