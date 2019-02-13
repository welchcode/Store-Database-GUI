import java.sql.*;
/*
 * Database class that connects GUI to local PostgreSQL database
 * 
 * */
public class DB {
	//declaration of private vars
	private static String url = "jdbc:postgresql://localhost:5432/store_db";		// default URL used to connect 
	private static Connection conn;
	private static Statement stmt;
	
	//Main Database Method
	public static void connect() throws SQLException {
		conn = DriverManager.getConnection(url,null,null);						//getConnection used for connecting program to local DB
		if(conn != null)
			System.out.println("Database connected successfully");				
	}
	
	
	/*
	 * 	Adds employee to the Employee table of the database
	 * */
	public static void addEmployee(String id, String firstName, String lastName,  String age, String streetAddress, String state, String salary) throws SQLException {
		stmt = conn.createStatement();
		String sql = "INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,AGE,ADDRESS,STATE,SALARY) " + 								//SQL statement for adding employee to table
				"VALUES "+"('"+id+"',"+"'"+firstName+"','"+lastName+"','"+age+"','"+streetAddress+"','"+state+"','"+salary+"')";			
		stmt.executeUpdate(sql);																									//exeuting update
		stmt.close();
		System.out.println("Update successful");
	}
	
	/*
	 * 	Adds item to Item table of database
	 * */
	public static void addItem(String id, String name, String price) throws SQLException {
		stmt = conn.createStatement();
		String sql = "INSERT INTO ITEM (ID,NAME,PRICE) " + 								//SQL statement for adding items to item table
					"VALUES "+"('"+id+"','"+name+"','"+price+"')";
		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("Update successful");
	}
	
	//this class runs a query on the item database
	//so far, items can only be found using ID number -- in the future this will display a final "checkout" screen
	public static void checkOut(String id) throws SQLException {
		
		stmt = conn.createStatement();									//stmt 1
		Statement stmt2 = conn.createStatement();						//stmt 2
		
		int barcode = -1;												//init barcodeID
		String name = null;												//init String name
		String price = null;												//init String price
				
		String sql = "SELECT * FROM ITEM"								//Query SQL for finding item in Item table
				+" WHERE id=" + id;
		String sql2 = null;
		
		ResultSet rs = stmt.executeQuery(sql);							//init resultSet with the query
		
		while(rs.next()) {												//while the resultSet has next, assign barcode, name, and price with strings from database
			barcode = rs.getInt("id");
			name = rs.getString("name");
			price = rs.getString("price");
		}
		/*
		 * 	This portion of this method is still in testing phase
		 * */
		sql2 = "INSERT INTO CHECKED_OUT (ID,NAME,PRICE) "					//this code is not ready yet -- still in testing phase
				+"VALUES "+"("+barcode+","+"'"+name+"','"+price+"')";	
		stmt2.execute(sql2);												
		stmt.close();
		stmt2.close();
	}
}
