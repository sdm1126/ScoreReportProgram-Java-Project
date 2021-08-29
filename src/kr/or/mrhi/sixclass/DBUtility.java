package kr.or.mrhi.sixclass;

import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtility {

	public static Connection getConnection() {
		
		Connection connection = null;
		
		try {
			// Read properties(DRIVER, URL, ID, PASSWORD)(getting path of db.properties)
			String filePath = DBUtility.class.getResource("db.properties").getPath();
			filePath = URLDecoder.decode(filePath, "UTF-8");
			Properties properties = new Properties();
			properties.load(new FileReader(filePath));
			
			// Bind properties(getting information of db.properties)
			String driver = properties.getProperty("DRIVER");
			String url = properties.getProperty("URL");
			String userId = properties.getProperty("USERID");
			String userPassword = properties.getProperty("USERPASSWORD");
			
			// Load JDBC driver
			Class.forName(driver);
			
			// Connect to Database
			connection = DriverManager.getConnection(url, userId, userPassword);
			
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다.");
			
		}
		
		return connection;
	}
	
}
