package DataBase;
import DataObjects.UserData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class InsertData {

    public void InsertIntoDB(){

        UserData myUser = new UserData(
                "Luka",
                "Emrashvili",
                "555123456",
                "lemra4444",
                "Street #3",
                "lukaemra:@gmail.com",
                "Georgia",
                "Rustavi",
                "30305");

        // split email to insert unique number inside it
        String[] email_parts = myUser.getEmail().split(":");

        try(Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433",
                "TestAutomation", "TestAutomation123");
        ) {

            // Insert a New Row
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO [users].[dbo].[users]\n" +
                    "(firstName,lastName,phone,password,address,email,state,city,zip)\n" +
                    "VALUES('"+myUser.getFirst_name()+"','"+myUser.getLast_name()+"'," +
                    "'"+ myUser.getPhone_num()+"','"+ myUser.getPassword()+"'," +
                    "'"+ myUser.getAddress()+"','"+ email_parts[0]+new Date().getTime()+email_parts[1]+"'," +  // adding random number in between the email parts
                    "'"+ myUser.getState()+"','"+ myUser.getCity()+"',"+ Integer.parseInt(myUser.getZip_code())+")");
        }
        catch (SQLException e) {System.out.println(e.getMessage());}
    }
}
