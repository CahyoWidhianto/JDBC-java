package tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

  private Connection connection;

  public Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/db_hr_sibkm?zeroDateTimeBehavior=convertToNull",
          "root",
          "");
    } catch (Exception e) {
      System.out.println("Error Message = " + e.getMessage());
    }

    return connection;
  }
}
