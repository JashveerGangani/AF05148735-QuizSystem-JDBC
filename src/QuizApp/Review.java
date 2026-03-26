package QuizApp;

public class Review {
    String question;
    int correctAns;
    int userAns;

    public Review(String question, int correctAns, int userAns) {
        this.question = question;
        this.correctAns = correctAns;
        this.userAns = userAns;
    }
}