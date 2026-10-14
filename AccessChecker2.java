
public class AccessChecker2 {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "DENIED";
            case "default":
                return accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
            case "protected":
                if (accessorContext.equals("SAME_PACKAGE") || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                    return "ALLOWED";
                return "DENIED";
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }
    public static String describeContext(String accessorContext) {
        String[] words = accessorContext.toLowerCase().split("_");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (result.length() > 0)
                result.append(" ");
            result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }
        return result.toString();
    }
    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}
