import java.sql.*;
import java.util.ArrayList;
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

        try {
            Statement statement = DBConnection.getConnection().createStatement();
            statement.executeQuery("SELECT * FROM loaners");

            ResultSet result = statement.getResultSet();

            while (result.next()) {
                int idloaners = result.getInt("idloaners");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String books = result.getString("books");
                String username = result.getString("username");

                User user = new User(idloaners, firstname, lastname, books, username);

                ArrayList<User> users = new ArrayList<>();
                users.add(user);

                for (User u : users) {
                    System.out.println(u);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void loanBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in your username");
        String username = scanner.next();
        System.out.println("Type in the book you want to loan");
        String book = scanner.next();
        updateLoaner(username, book);
    }

    public static void updateLoaner(String username, String books) {

        try {
            String sql = "UPDATE loaners Set books = ? WHERE username = ?";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, books);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();

        }
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

    public static void searchForUser (Scanner input){

        System.out.println("Search for a books");
        String searchForAUser = input.next();

        String sql = "SELECT * FROM books WHERE name LIKE " + "'%" + searchForAUser + "%'";

        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");

                String user = name;

                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
