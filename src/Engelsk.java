import java.util.Scanner;

public class Engelsk extends Dialog {
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
        return "select dk/eng/fi/deu";
    }

    @Override
    public String loanBook() {
        System.out.println("chose a book to loan");
        //metode her
        return null;
    }

    @Override
    public void mainMenu() {

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
                    //createLoaner();
                    break;
                case 2:
                    loanBook();
                    break;
                case 3:
                    //createBook();
                    break;
                case 4:
                    //userInformation();
                    break;

            }
        }
    }


}
