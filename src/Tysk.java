import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Tysk extends Dialog {
    DBConnection dbConnection = new DBConnection();
    Scanner scanner = new Scanner(System.in);
    @Override
    public String hi() {
        return "Hallo und herzlich willkommen";
    }

    @Override
    public String changeLanguage() {
        return "Möchten Sie die Sprache ändern, um die Sprache zu ändern";
    }

    @Override
    public String selectLanguage() {
        return "Eintreten dk, eng, deu oder gehen Sie zum Menü";
    }

    @Override
    public void loanBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie Ihren Benutzernamen ein");
        String username = scanner.next();
        System.out.println("Geben Sie das Buch ein, das Sie ausleihen möchten");
        String book = scanner.next();
        Menu.updateLoaner(username, book);
    }

    @Override
    public void mainMenu() {


        int answer;

        answer = 1;

        while (answer != 0) {
            System.out.println("Drücken Sie '1', um einen Benutzer zu erstellen");
            System.out.println("Drücken Sie '2', um ein Buch auszuleihen");
            System.out.println("Drücken Sie '3', um ein Buch zur Bibliothek hinzuzufügen");
            System.out.println("Drücken Sie '4', um Benutzerinformationen anzuzeigen");
            System.out.println("Drücken Sie '5', um die Sprache auszuwählen");

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
                String firstname = result.getString("Vorname");
                String lastname = result.getString("Familienname");
                String books = result.getString("Bücher");
                String username = result.getString("Nutzername");

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

            ps.setString(1, Menu.getString("Geben Sie den Vornamen ein:"));
            ps.setString(2, Menu.getString("Geben Sie den Nachnamen ein"));
            ps.setString(3, Menu.getString("Geben Sie den Benutzernamen ein"));

            ps.executeUpdate();

            System.out.println("\nSie haben nun einen Benutzer angelegt.\n");

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

            ps.setString(1, Menu.getString("Geben Sie den Namen ein"));
            ps.setString(2, Menu.getString("Geben Sie den Autor ein"));
            ps.setString(3, Menu.getString("Jahr eingeben"));
            ps.setString(4, Menu.getString("Geben Sie die Anzahl der Kopien ein"));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
