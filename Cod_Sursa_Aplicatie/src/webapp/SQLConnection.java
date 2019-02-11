package webapp;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class SQLConnection {
    private static Connection ourInstance;
    private static String url = "jdbc:mysql://localhost/AnunturiImobiliareV2.0";
    private static String username = "tudorindan";
    private static String password = "123Dan3211@";

    public static Connection getConnection() {

        if (ourInstance == null) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ourInstance = DriverManager.getConnection(url, username, password);
                System.out.println("Done");

            } catch (SQLException e) {

                e.printStackTrace();

            }
        }

        return ourInstance;

    }

    private SQLConnection() {
    }
}
