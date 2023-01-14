import java.util.ArrayList;
import java.util.Scanner;

public class Q_A {
    private static ArrayList<String> questions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Zadaj pytanie");
            System.out.println("2. Usuń pytanie");
            System.out.println("3. Zakończ");
            System.out.print("Zatwierdź wybór: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    askQuestion(scanner);
                    break;
                case 2:
                    removeQuestion(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Źle, spróbuj jeszcze raz");
            }
        }
    }

    private static void askQuestion(Scanner scanner) {
        System.out.print("Zadaj pytanie: ");
        String question = scanner.nextLine();
        scanner.nextLine();
        questions.add(question);
        System.out.println("Udało się zadać pytanie!!");
    }

    private static void removeQuestion(Scanner scanner) {
        if (questions.isEmpty()) {
            System.out.println("Usuń pytanie");
            return;
        }

        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i));
        }

        System.out.print("Wybierz pytanie które chcesz usunąć: ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= questions.size()) {
            System.out.println("Wybierz ponownie");
            return;
        }

        questions.remove(index);
        System.out.println("Pyanie zostało zmienione!");
    }
}
