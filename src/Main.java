import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int answer;

        answer = 1;

        while (answer!=0) {
            System.out.println("Press '1' to create ");
            System.out.println("Press '2' to login");

            answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    create();
                    break;
                case 2:
                    login();
                    break;
            }
        }
    }
    public static String getString (String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void create() {
        String sql = "INSERT INTO Loaners (firstname, lastname) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, getString("Type in firstname: "));
            ps.setString(2, getString("Type in lastname"));

            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            System.out.println("Next row: " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void login() {

    }

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/Bibliotek?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "pllghp1d";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}