import java.util.Scanner;

public class Language {
    public static void selectLanguage () {

        Scanner scanner = new Scanner(System.in);

        String ans = "";

        Dialog dialog = new Engelsk();

        while (true) {
            System.out.println(dialog.hi());
//          System.out.println(dialog.changeLanguage());
//          ans = scanner.nextLine();


            if (Input.getString(dialog.changeLanguage()).equalsIgnoreCase("y")) {
                System.out.println(dialog.selectLanguage());
                ans = scanner.nextLine();

                switch (ans) {
                    case "dk":
                        dialog = new Dansk();
                        break;

                    case "eng":
                        dialog = new Engelsk();
                        break;


                    case "fi":
                        dialog = new Finsk();
                        break;

                    case "deu":
                        dialog = new Tysk();
                        break;

                    default:
                        dialog = new Engelsk();


                }
            }

        }
    }
}
