
public class TicketSettlement {
    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);
        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println(t1.getBalanceDue());
        System.out.println(t1.ticketId);
        System.out.println(EventTicket.isValidPromoCode("F123A"));
        System.out.println(EventTicket.processNightlySettlement(new EventTicket[]{new GroupTicket(2000, 5), null, new EventTicket(500)}));
    }
}
class EventTicket {
    private static int counter = 1000;
    public final String ticketId;
    protected double basePrice;
    protected double amountPaid;
    public EventTicket(double basePrice) {
        if (basePrice <= 0)
            throw new IllegalArgumentException();
        ticketId = "TCK-" + (++counter);
        this.basePrice = basePrice;
    }
    public void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }
    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }
    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }
    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5 || code.charAt(0) != 'F' || !Character.isUpperCase(code.charAt(4)))
            return false;
        for (int i = 1; i < 4; i++)
            if (!Character.isDigit(code.charAt(i)))
                return false;
        return true;
    }
    public static int getTicketsIssued() {
        return counter - 1000;
    }
    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0, skipped = 0, groups = 0, individuals = 0;
        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                skipped++;
            } else {
                processed++;
                if (ticket instanceof GroupTicket)
                    groups++;
                else
                    individuals++;
            }
        }
        return processed + " processed | " + skipped + " null skipped | " + groups + " group | " + individuals + " individual";
    }
}
class GroupTicket extends EventTicket {
    private int groupSize;
    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        if (groupSize <= 0)
            throw new IllegalArgumentException();
        this.groupSize = groupSize;
    }
}
