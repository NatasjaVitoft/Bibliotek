import java.util.Scanner;

public class Dansk extends Dialog
{
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
        return "angiv dk/eng/fi/deu";
    }

    @Override
    public String loanBook() {
        System.out.println("vælg en bog at låne");
        //metode her
        return null;
    }

    @Override
    public void mainMenu() {

        Scanner scanner = new Scanner(System.in);

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
