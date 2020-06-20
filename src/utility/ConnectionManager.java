package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		//register driver class
		Class.forName("oracle.jdbc.OracleDriver");
		
		//create connection object
		Connection con = null;
		
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","1998");
		
		return con;
	}
}
