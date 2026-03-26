package QuizApp;

import java.sql.*;
import java.util.*;

public class QuizDAO {

    // Add Question
    public void addQuestion(String q, String o1, String o2, String o3, String o4, int ans) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO questions(question, option1, option2, option3, option4, answer) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, q);
            ps.setString(2, o1);
            ps.setString(3, o2);
            ps.setString(4, o3);
            ps.setString(5, o4);
            ps.setInt(6, ans);

            ps.executeUpdate();
            System.out.println("Question Added Successfully ✅");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Start Quiz (WITH REVIEW 🔥)
    public void startQuiz(String username) {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM questions");

            List<Question> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new Question(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                ));
            }

            Collections.shuffle(list);

            Scanner sc = new Scanner(System.in);
            int score = 0;

            List<Review> reviewList = new ArrayList<>();

            for (Question q : list) {
                System.out.println("\n" + q.question);
                System.out.println("1. " + q.o1);
                System.out.println("2. " + q.o2);
                System.out.println("3. " + q.o3);
                System.out.println("4. " + q.o4);

                System.out.print("Your Answer: ");
                int userAns = sc.nextInt();

                if (userAns == q.answer) {
                    score++;
                }

                reviewList.add(new Review(q.question, q.answer, userAns));
            }

            int total = list.size();
            double percent = (score * 100.0) / total;

            System.out.println("\n===== RESULT =====");
            System.out.println("Score: " + score + "/" + total);
            System.out.println("Percentage: " + percent + "%");

            // Save result
            String query = "INSERT INTO results(username, score, total, percentage) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setInt(2, score);
            ps.setInt(3, total);
            ps.setDouble(4, percent);

            ps.executeUpdate();

            System.out.println("Result Saved ✅");

            // 🔥 REVIEW SECTION
            System.out.println("\n===== REVIEW ANSWERS =====");

            for (Review r : reviewList) {
                System.out.println("\nQuestion: " + r.question);
                System.out.println("Your Answer: " + r.userAns);
                System.out.println("Correct Answer: " + r.correctAns);

                if (r.userAns == r.correctAns)
                    System.out.println("Status: Correct ✅");
                else
                    System.out.println("Status: Wrong ❌");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Leaderboard
    public void showTopScores() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM results ORDER BY score DESC LIMIT 5");

            System.out.println("\n===== TOP SCORES =====");

            while (rs.next()) {
                System.out.println(rs.getString("username") +
                        " | Score: " + rs.getInt("score") +
                        " | %: " + rs.getDouble("percentage"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}