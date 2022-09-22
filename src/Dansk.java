import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Dansk extends Dialog {
    DBConnection dbConnection = new DBConnection();
    Scanner scanner = new Scanner(System.in);
    @Override
    public String hi() {
        return "Hej og velkommen !";
    }

    @Override
    public String changeLanguage() {
        return "vil du skifte sprog skift sprog";
    }

    @Override
    public String selectLanguage() {
        return "angiv dk, eng, deu or menu";
    }

    @Override
    public void loanBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv dit brugernavn");
        String username = scanner.next();
        System.out.println("Skriv hvilken bog du gerne vil låne");
        String book = scanner.next();
        Menu.updateLoaner(username, book);
    }

    @Override
    public void mainMenu() {


        int answer;

        answer = 1;

        while (answer != 0) {
            System.out.println("Tryk '1' for at lave en bruger");
            System.out.println("Tryk '2' for at låne en bog");
            System.out.println("Tryk '3' for at tilføje en bog til biblioteket");
            System.out.println("Tryk '4' for at se bruger information");
            System.out.println("Tryk '5' for at skifte sprog");

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
                String firstname = result.getString("fornavn");
                String lastname = result.getString("efternavn");
                String books = result.getString("bøger");
                String username = result.getString("bruger");

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

            ps.setString(1, Menu.getString("Skriv dit fornavn: "));
            ps.setString(2, Menu.getString("Skriv dit efternavn:"));
            ps.setString(3, Menu.getString("Skriv dit brugernavn:"));

            ps.executeUpdate();

            System.out.println("\nDu har nu lavet en bruger. \n");

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

            ps.setString(1, Menu.getString("Skriv navn"));
            ps.setString(2, Menu.getString("Skriv forfatter"));
            ps.setString(3, Menu.getString("Skriv år"));
            ps.setString(4, Menu.getString("Skriv antal kopier"));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
