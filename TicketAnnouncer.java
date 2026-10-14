
public class TicketAnnouncer {
    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder report = new StringBuilder();
        for (EventTicket ticket : tickets) {
            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket w = (WorkshopTicket) ticket;
                report.append("Workshop | Track: ").append(w.getTrack()).append(" | Balance: ").append(w.getBalanceDue()).append(" [Track via downcast: ").append(w.getTrack()).append("] | ");
            } else {
                report.append("Standard | Balance: ").append(ticket.getBalanceDue()).append(" | ");
            }
        }
        return report.toString();
    }
    public static void main(String[] args) {
        EventTicket[] tickets = {
            new EventTicket(500),
            new WorkshopTicket(1200, "AI/ML")
        };
        System.out.println(batchPrint(tickets));
    }
}
class EventTicket {
    protected double basePrice;
    protected double amountPaid;
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
    public void printTicket() {
        System.out.print("Standard | Balance: " + getBalanceDue());
    }
}
class WorkshopTicket extends EventTicket {
    private String track;
    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }
    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
    public String getTrack() {
        return track;
    }
    @Override
    public void printTicket() {
        System.out.print("Workshop | Track: " + track + " | Balance: " + getBalanceDue());
    }
}
