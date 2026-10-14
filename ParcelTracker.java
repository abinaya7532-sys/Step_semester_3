
import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
    String getName();
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }

    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }

    public String getName() {
        return "Express";
    }
}

class FragileShipping implements ShippingType {
    private final ShippingType standard = new StandardShipping();

    public double calculateCharge(double weight) {
        return standard.calculateCharge(weight) + 50;
    }

    public String getName() {
        return "Fragile";
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {
    public void notify(String id, String status) {
        System.out.println("[SMS] " + id + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String id, String status) {
        System.out.println("[Email] " + id + " is now " + status + ".");
    }
}

class Parcel {
    String id;
    double weight;
    ShippingType shipping;
    String status = "BOOKED";
    List<NotificationChannel> channels;

    Parcel(String id, double weight, ShippingType shipping,
           List<NotificationChannel> channels) {
        this.id = id;
        this.weight = weight;
        this.shipping = shipping;
        this.channels = channels;
    }

    double charge() {
        return shipping.calculateCharge(weight);
    }

    void updateStatus(String next) {
        String[] states = {"BOOKED", "PICKED_UP", "IN_TRANSIT",
                "OUT_FOR_DELIVERY", "DELIVERED"};

        int current = Arrays.asList(states).indexOf(status);
        int target = Arrays.asList(states).indexOf(next);

        if (current < 0 || target != current + 1) {
            System.out.println("Invalid transition: " + status + " → " + next + " is not allowed.");
            return;
        }

        status = next;
        for (NotificationChannel c : channels) {
            c.notify(id, status);
        }
    }

    void cancel() {
        if (!status.equals("BOOKED")) {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
            return;
        }

        status = "CANCELLED";
        System.out.println("Parcel " + id + " cancelled.");
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

public class ParcelTracker {
    public static void main(String[] args) {
        List<NotificationChannel> channels = Arrays.asList(
                new SmsChannel(), new EmailChannel());

        Parcel p = new Parcel("P101", 2, new ExpressShipping(), channels);

        System.out.println("Parcel P101 booked (Express, 2 kg).");
        System.out.printf("Charge: ₹%.2f%n", p.charge());

        for (NotificationChannel c : channels) {
            c.notify(p.id, p.status);
        }

        p.updateStatus("PICKED_UP");
        p.cancel();
        p.updateStatus("IN_TRANSIT");
        p.updateStatus("DELIVERED");
    }
}
