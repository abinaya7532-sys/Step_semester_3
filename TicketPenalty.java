
import java.util.Arrays;
public class TicketPenalty {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue());
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
        double[] history = w.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}
class EventTicket {
    protected double basePrice;
    protected double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int feeCount;
    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }
    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException();
        this.basePrice = basePrice;
    }
    public void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }
    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }
    protected void applyLateFee(double amount) {
        basePrice += amount;
        if (feeCount < lateFeeHistory.length)
            lateFeeHistory[feeCount++] = amount;
    }
    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}
class WorkshopTicket extends EventTicket {
    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }
    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
    }
    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}
