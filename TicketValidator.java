
public class TicketValidator {
    public static void main(String[] args) {
        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};
        System.out.println(EventTicket.registerBatch(ids, 500));
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());
    }
}
class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;
    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException();
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }
    public void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }
    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }
    public void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " + getBalanceDue());
    }
    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0, rejected = 0;
        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}
class WorkshopTicket extends EventTicket {
    protected String track;
    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
    @Override
    public void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue());
    }
}
