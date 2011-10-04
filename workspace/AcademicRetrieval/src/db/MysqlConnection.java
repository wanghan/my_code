/**
 * 
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author wanghan
 *
 */
public class MysqlConnection {
	
	private static Connection connection=null;
	
	public static Connection getConnection(){
		if(connection==null){
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				String url = "jdbc:mysql://localhost:3306/academic_data?user=root&password=123456"; 
				connection = DriverManager.getConnection(url); 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		return connection;
	}
	public static void reset() throws SQLException{
		if(connection!=null){
			connection.close();
			connection=null;
			System.gc();
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				String url = "jdbc:mysql://localhost:3306/academic_data?user=root&password=123456"; 
				connection = DriverManager.getConnection(url); 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
}
