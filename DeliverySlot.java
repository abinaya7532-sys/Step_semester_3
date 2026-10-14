
public class DeliverySlot {
    private final String orderId;
    private final String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        if (orderId == null || orderId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid order ID");
        if (timeSlot == null || timeSlot.trim().isEmpty())
            throw new IllegalArgumentException("Invalid time slot");
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00") ||
               timeSlot.equals("13:00-14:00") ||
               timeSlot.equals("19:00-20:00") ||
               timeSlot.equals("20:00-21:00");
    }

    public static void main(String[] args) {
        DeliverySlot d1 = new DeliverySlot("ORD101", "13:00-14:00");
        DeliverySlot d2 = new DeliverySlot("ORD102");

        System.out.println(d1.isPeakHour());
        System.out.println(d2.isPeakHour());
    }
}
