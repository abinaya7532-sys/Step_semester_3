
public class DeliveryAccount {
    private final String studentId;
    private final double orderValue;
    private static final SurgeFeeCalculator CALCULATOR;

    static {
        CALCULATOR = new SurgeFeeCalculator(1.0);
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid student ID");
        if (!Double.isFinite(orderValue) || orderValue < 0)
            throw new IllegalArgumentException("Invalid order value");
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        return CALCULATOR.calculateSurgeFee(orderValue, delayMinutes);
    }

    public String getStudentId() {
        return studentId;
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null)
            return;
        if (!Double.isFinite(amount) || amount < 0 || delayMinutes < 0)
            throw new IllegalArgumentException("Invalid reconciliation data");

        double fee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumDeliveryAccount)
            fee *= 0.90;

        System.out.printf("%s | Amount: %.2f | Surge fee: %.2f%n",
            account.studentId, amount, fee);
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null ||
            accounts.length != amounts.length || accounts.length != delayMinutesArray.length)
            throw new IllegalArgumentException("All arrays must have matching lengths");

        int processed = 0, nullSkipped = 0, premiumCount = 0, regularCount = 0;
        double totalFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            if (!Double.isFinite(amounts[i]) || amounts[i] < 0 || delayMinutesArray[i] < 0)
                continue;

            double fee = account.calculateSurgeFee(delayMinutesArray[i]);

            if (account instanceof PremiumDeliveryAccount) {
                fee *= 0.90;
                premiumCount++;
            } else {
                regularCount++;
            }

            System.out.printf("%s | Amount: %.2f | Surge fee: %.2f%n",
                account.studentId, amounts[i], fee);

            totalFees += fee;
            processed++;
        }

        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = %.2f%n",
            processed, nullSkipped, premiumCount, regularCount, totalFees);
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }
}
