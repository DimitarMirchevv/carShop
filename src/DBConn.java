import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    static Connection conn = null;

    static Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:h2:tcp://localhost/D:\\Uni\\Втори семестър\\Практикум\\personDB./test",
                    "sa", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
