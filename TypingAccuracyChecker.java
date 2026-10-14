import java.util.Scanner;

public class TypingAccuracyChecker {
    static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatch = -1;
        int length = Math.max(original.length(), typed.length());

        for (int i = 0; i < length; i++) {
            if (i < original.length() && i < typed.length()
                    && original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = length == 0 ? 100 : matched * 100.0 / length;
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%%n",
                matched, length, accuracy);

        if (firstMismatch == -1)
            System.out.println("No Mismatches");
        else
            System.out.println("First Mismatch at position " + (firstMismatch + 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter original passage: ");
        String original = sc.nextLine();
        System.out.print("Enter typed passage: ");
        String typed = sc.nextLine();

        checkTypingAccuracy(original, typed);
        sc.close();
    }
}