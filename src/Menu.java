import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {

    DBConnection dbConnection = new DBConnection();

    public void mainMenu () {

        Scanner scanner = new Scanner(System.in);

        int answer;

        answer = 1;

        while (answer != 0) {
            System.out.println("Press '1' to create a user");
            System.out.println("Press '2' to loan a book");
            System.out.println("Press '3' to add a book to the library");
            System.out.println("Press '4' to see user information");

            answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    createLoaner();
                    break;
                case 2:
                    loanBook();
                    break;
                case 3:
                    createBook();
                    break;
                case 4:
                    userInformation();
                    break;

            }
        }
    }

    public static String getString (String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void userInformation() {

    }

    public static void loanBook() {

    }

    public static void createLoaner() {
        String sql = "INSERT INTO Loaners (firstname, lastname, username) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, getString("Type in firstname: "));
            ps.setString(2, getString("Type in lastname"));
            ps.setString(3, getString("Type in username"));

            ps.executeUpdate();

            System.out.println("\nYou have now created a user.\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createBook() {
        String sql = "INSERT INTO books (name, author, year, copies) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
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
}
