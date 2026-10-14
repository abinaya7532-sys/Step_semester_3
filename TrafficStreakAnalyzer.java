import java.util.Scanner;

public class TrafficStreakAnalyzer {
    static void findLongestStreak(String signalLog) {
        if (signalLog.length() == 0) {
            System.out.println("No Signal Data");
            return;
        }

        int current = 1, longest = 1;
        char currentColor = signalLog.charAt(0);
        char longestColor = currentColor;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                current++;
            } else {
                current = 1;
            }

            if (current > longest) {
                longest = current;
                longestColor = signalLog.charAt(i);
            }
        }

        System.out.println("Longest Streak: '" + longestColor +
                "' repeated " + longest + " times");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter signal log: ");
        String log = sc.nextLine().toUpperCase();

        findLongestStreak(log);
        sc.close();
    }
}