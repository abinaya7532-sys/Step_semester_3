
import java.util.*;

public class WordFrequencyReport {
    public static void printFilteredWordFrequency(String feedback) {
        String cleaned = feedback.toLowerCase()
            .replace(".", "")
            .replace(",", "")
            .replace("!", "")
            .replace("?", "");

        String[] words = cleaned.trim().split("\\s+");
        Set<String> stopWords = new HashSet<>(
            Arrays.asList("the", "was", "and", "a", "is", "of", "in")
        );
        Map<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word))
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries =
            new ArrayList<>(frequency.entrySet());

        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : entries)
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}
