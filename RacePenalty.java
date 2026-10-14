
import java.util.Arrays;
public class RacePenalty {
    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
        double[] history = r.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}
class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int feeCount;
    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException();
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }
    public void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }
    public double getBalanceDue() {
        return Math.max(0, entryFee - amountPaid);
    }
    protected void applyLateFee(double amount) {
        entryFee += amount;
        if (feeCount < lateFeeHistory.length)
            lateFeeHistory[feeCount++] = amount;
    }
    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}
class RunnerEntry extends RaceEntry {
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
    }
    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}
