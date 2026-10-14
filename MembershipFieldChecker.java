
import java.util.LinkedHashMap;
import java.util.Map;
public class MembershipFieldChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
            case "default":
            case "protected":
                return accessorContext.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }
    public static String summarizeByModifier(String[][] attempts) {
        Map<String, int[]> counts = new LinkedHashMap<>();
        for (String[] attempt : attempts) {
            counts.putIfAbsent(attempt[0], new int[2]);
            int[] result = counts.get(attempt[0]);
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                result[0]++;
            else
                result[1]++;
        }
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, int[]> entry : counts.entrySet()) {
            if (output.length() > 0)
                output.append(" | ");
            output.append(entry.getKey()).append(": ")
                .append(entry.getValue()[0]).append(" allowed / ")
                .append(entry.getValue()[1]).append(" denied");
        }
        return output.toString();
    }
    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(attempts));
    }
}
class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;
    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid membership ID");
        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}
