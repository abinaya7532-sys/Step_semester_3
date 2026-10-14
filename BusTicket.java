
import java.util.*;

public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn = false;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || !passengerName.trim().matches("[\\p{L}]+([ '-][\\p{L}]+)*"))
            throw new IllegalArgumentException("Invalid passenger name");
        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Invalid destination");
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    public void markCheckedIn() {
        if (checkedIn)
            System.out.println("Already checked in");
        else {
            checkedIn = true;
            System.out.println("Checked in successfully");
        }
    }

    public static void processBatch(String[][] rawBookings) {
        Set<String> acceptedBookings = new HashSet<>();
        int valid = 0, rejected = 0, duplicates = 0;

        for (String[] booking : rawBookings) {
            try {
                if (booking == null || booking.length < 2)
                    throw new IllegalArgumentException();

                BusTicket ticket = new BusTicket(booking[0], booking[1]);
                String key = ticket.passengerName.toLowerCase(Locale.ROOT) + "\u0000" + ticket.destination.toLowerCase(Locale.ROOT);

                if (acceptedBookings.contains(key))
                    duplicates++;
                else {
                    acceptedBookings.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        processBatch(bookings);
    }
}
