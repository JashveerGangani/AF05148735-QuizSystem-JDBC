package QuizApp;

import java.util.Scanner;

public class UserMenu {

    public void start() {
        Scanner sc = new Scanner(System.in);
        QuizDAO dao = new QuizDAO();

        while (true) {
            System.out.println("\n===== QUIZ MENU =====");
            System.out.println("1. Add Question");
            System.out.println("2. Start Quiz");
            System.out.println("3. View Top Scores");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    sc.nextLine();

                    System.out.print("Enter Question: ");
                    String q = sc.nextLine();

                    System.out.print("Option 1: ");
                    String o1 = sc.nextLine();

                    System.out.print("Option 2: ");
                    String o2 = sc.nextLine();

                    System.out.print("Option 3: ");
                    String o3 = sc.nextLine();

                    System.out.print("Option 4: ");
                    String o4 = sc.nextLine();

                    System.out.print("Correct Answer (1-4): ");
                    int ans = sc.nextInt();

                    dao.addQuestion(q, o1, o2, o3, o4, ans);
                    break;

                case 2:
                    sc.nextLine();

                    System.out.print("Enter Username: ");
                    String name = sc.nextLine();

                    dao.startQuiz(name);
                    break;

                case 3:
                    dao.showTopScores();
                    break;

                case 4:
                    System.out.println("Thank You!");
                    System.exit(0);
            }
        }
    }
}