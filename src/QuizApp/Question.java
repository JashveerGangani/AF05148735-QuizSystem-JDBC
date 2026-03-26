package QuizApp;

public class Question {
    int id;
    String question, o1, o2, o3, o4;
    int answer;

    public Question(int id, String question, String o1, String o2, String o3, String o4, int answer) {
        this.id = id;
        this.question = question;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.answer = answer;
    }
}