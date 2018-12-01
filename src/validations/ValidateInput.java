package validations;

import java.util.Scanner;

public class ValidateInput {

    public int ValidateInput() {
        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            // input check
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                //System.out.printf("\"%s\" is not a valid option.\n", input);
            }
            number = scanner.nextInt();
        } while (!(number == 1 || number == 2 || number == 3 || number == 4 || number == 5));


        return number;
    }
}
