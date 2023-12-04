import java.util.Scanner;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOption;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private QuizQuestion[] questions;
    private int userScore;
    private static final int TIME_LIMIT_SECONDS = 10; // Time limit for each question in seconds

    public Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        this.userScore = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            QuizQuestion currentQuestion = questions[i];
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestion());

            // Display options
            String[] options = currentQuestion.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((char) ('A' + j) + ". " + options[j]);
            }

            // Start timer thread
            TimerThread timerThread = new TimerThread(TIME_LIMIT_SECONDS);
            timerThread.start();

            // Get user input
            System.out.print("Your answer (A/B/C/D): ");
            String userAnswer = scanner.next().toUpperCase();

            // Stop the timer
            timerThread.stopTimer();

            // Check user answer
            int userChoice = userAnswer.charAt(0) - 'A';
            if (userChoice == currentQuestion.getCorrectOption()) {
                System.out.println("Correct!\n");
                userScore++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + (char) ('A' + currentQuestion.getCorrectOption()) + "\n");
            }
        }

        // Display final score
        System.out.println("Quiz completed! Your final score: " + userScore + " out of " + questions.length);

        // Close the scanner
        scanner.close();
    }
}

class TimerThread extends Thread {
    private int timeLimitSeconds;
    private boolean timeUp;

    public TimerThread(int timeLimitSeconds) {
        this.timeLimitSeconds = timeLimitSeconds;
        this.timeUp = false;
    }

    public void run() {
        try {
            Thread.sleep(timeLimitSeconds * 1000);
            timeUp = true;
            System.out.println("\nTime's up!");
        } catch (InterruptedException e) {
            // Thread interrupted
        }
    }

    public void stopTimer() {
        interrupt();
    }

    public boolean isTimeUp() {
        return timeUp;
    }
}

public class Task4 {
    public static void main(String[] args) {
        // Sample quiz questions
        QuizQuestion[] quizQuestions = {
                new QuizQuestion("What is the capital of Chennai?",
                        new String[]{"Berlin", "Madras", "Rome", "Paris"}, 1),
                new QuizQuestion("what is the national flower of India?",
                        new String[]{"Lotus", "Rose", "Lily", "sunflower"}, 0),
                // Add more questions as needed
        };

        // Create a quiz with the sample questions
        Quiz quiz = new Quiz(quizQuestions);

        // Start the quiz
        quiz.startQuiz();
    }
}
