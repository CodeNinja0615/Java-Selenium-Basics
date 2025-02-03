package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JDBCconnection {

	public static void main(String[] args) throws SQLException {
		// Establishing the connection
		String dbUrl = "localhost";
		String dbName = "QAdbTest";
		String location = null;
		Connection con = DriverManager.getConnection("jdbc:mysql://"+dbUrl+":3306/"+dbName, "root", "Sameerking01!");

		// Creating a statement
		Statement statement = con.createStatement();

		// Executing the query
		ResultSet rs = statement.executeQuery("select * from EmployeeInfo where name = 'Steven';");
		
		
//		 // Get metadata
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnCount = rsmd.getColumnCount();
//
//        // Move the cursor to the first row
//        while (rs.next()) {
//            // Print all columns
//            for (int i = 1; i <= columnCount; i++) {
//                System.out.print(rsmd.getColumnName(i) + ": " + rs.getString(i) + " ");
//            }
//            System.out.println();
//        }
		
		
		// Move the cursor to the first row
		while (rs.next()) { //----while will get as much value as it has
			// Retrieve the data from the "location" column
			location = rs.getString("location");
			System.out.println(location);
		}
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys(location, Keys.ENTER);


		driver.quit();
		// Closing resources
		rs.close();
		statement.close();
		con.close();
	}
}

/*
 * create database QAdbTest; 
 * use QAdbTest; 
 * create table EmployeeInfo(name varchar(20), id int, location varchar(20), age int);
 * describe EmployeeInfo;
 * insert into EmployeeInfo values('Sameer',1,'Allahabad',22); 
 * insert into EmployeeInfo values('King',2,'Allahabad',21); 
 * insert into EmployeeInfo values('HeyHey',3,'Allahabad',20); insert into EmployeeInfo
 * insert into EmployeeInfo values('Sameer',1,'Asadha',22);
 * values('Steven',4,'Allahabad',29);
 * 
 * 
 * select * from EmployeeInfo;
 */