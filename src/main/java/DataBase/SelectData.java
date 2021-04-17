package DataBase;
import DataObjects.UserData;
import java.sql.*;

public class SelectData {

    public UserData selectFromDB(){

        UserData userData = null;

        try(Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433",
                "TestAutomation", "TestAutomation123");
        ) {
            // Select the last added user's data
            String query = "SELECT firstName,lastName,phone,password,address,email,state,city,zip " +
                    "FROM [users].[dbo].[users]" +
                    "WHERE id = (SELECT MAX(id) FROM [users].[dbo].[users])";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();

            // populate the object with returned values
            userData = new UserData(
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("phone"),
                    resultSet.getString("password"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("state"),
                    resultSet.getString("city"),
                    String.valueOf(resultSet.getInt("zip"))
            );
            return userData;
        }
        catch (SQLException e) {System.out.println(e.getMessage()); }

        return userData;
    }
}
