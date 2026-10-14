import java.util.Scanner;

public class WordLengthProfiler {
    static void classifyWordLengths(String review) {
        int shortWords = 0, mediumWords = 0, longWords = 0;
        String[] words = review.trim().split("\\s+");

        if (review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            int length = word.length();

            if (length == 0)
                continue;
            else if (length <= 4)
                shortWords++;
            else if (length <= 8)
                mediumWords++;
            else
                longWords++;
        }

        System.out.println("Short: " + shortWords +
                " | Medium: " + mediumWords +
                " | Long: " + longWords);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter movie review: ");
        String review = sc.nextLine();

        classifyWordLengths(review);
        sc.close();
    }
}