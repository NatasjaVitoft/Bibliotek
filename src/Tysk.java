import java.util.Scanner;

public class Tysk extends Dialog {
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
        return "Eintreten dk/eng/fi/deu";
    }

    @Override
    public String loanBook() {
        System.out.println("Wählen Sie ein Buch zum Ausleihen");
        //metode her
        return null;
    }

    @Override
    public void mainMenu() {

        Scanner scanner = new Scanner(System.in);

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
                case 5:
                    selectLanguage();
                    break;

            }
        }
    }
}
