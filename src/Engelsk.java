import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Engelsk extends Dialog {
    DBConnection dbConnection = new DBConnection();
    Scanner scanner = new Scanner(System.in);

    @Override
    public String hi() {
        return "Hi and welcome !";
    }

    @Override
    public String changeLanguage() {
        return "do you want to change language ?";
    }

    @Override
    public String selectLanguage() {
        return "select dk, eng, deu or menu";
    }

    @Override
    public void loanBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in your username");
        String username = scanner.next();
        System.out.println("Type in the book you want to loan");
        String book = scanner.next();
        Menu.updateLoaner(username, book);
    }

    @Override
    public void mainMenu() {

        int answer;

        answer = 1;

        while (answer != 0) {
            System.out.println("Press '1' to create a user");
            System.out.println("Press '2' to loan a book");
            System.out.println("Press '3' to add a book to the library");
            System.out.println("Press '4' to see user information");
            System.out.println("Press '5' to select language");

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
                case 5:
                    Language.selectLanguage();
                    break;

            }
        }
    }

    @Override
    public void userInformation() {

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

    @Override
    public void createLoaner() {
        String sql = "INSERT INTO Loaners (firstname, lastname, username) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, Menu.getString("Type in firstname: "));
            ps.setString(2, Menu.getString("Type in lastname"));
            ps.setString(3, Menu.getString("Type in username"));

            ps.executeUpdate();

            System.out.println("\nYou have now created a user.\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createBook() {
        String sql = "INSERT INTO books (name, author, year, copies) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, Menu.getString("Type name"));
            ps.setString(2, Menu.getString("Type author"));
            ps.setString(3, Menu.getString("Type year"));
            ps.setString(4, Menu.getString("Type number of copies"));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
