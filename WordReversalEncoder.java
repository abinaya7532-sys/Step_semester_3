
public class WordReversalEncoder {
    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            result.append(word.reverse());

            if (i < words.length - 1)
                result.append(" ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseEachWord("hello club"));
    }
}
