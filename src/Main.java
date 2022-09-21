import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int answer;

        answer = 1;

        while (answer!=0) {
            System.out.println("Press '1' to create a user");
            System.out.println("Press '2' to loan a book");
            System.out.println("Press '3' to add a book to the library");

            answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    create();
                    break;
                case 2:
                    loanBook();
                    break;
                case 3:
                    createBook();
                    break;
            }
        }
    }





    public static String getString (String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static int getInt (int i) {
        System.out.println(i);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void create() {
        String sql = "INSERT INTO Loaners (firstname, lastname) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, getString("Type in firstname: "));
            ps.setString(2, getString("Type in lastname"));

            ps.executeUpdate();

            System.out.println("\nYou have now created a user.\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loanBook() {

    }

    public static void createBook() {
        String sql = "INSERT INTO books (name, author, year, copies) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, getString("Type name"));
            ps.setString(2, getString("Type author"));
            ps.setString(3, getString("Type year"));
            ps.setString(4, getString("Type number of copies"));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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