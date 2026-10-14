
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid trip ID");
        if (!Double.isFinite(totalFare) || totalFare < 0)
            throw new IllegalArgumentException("Invalid fare");
        if (passengerCount <= 0)
            throw new IllegalArgumentException("Invalid passenger count");
        this.tripId = tripId;
        this.totalFare = BigDecimal.valueOf(totalFare).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0);
    }

    public double[] fareBreakdown() {
        long totalCents = Math.round(totalFare * 100);
        long shareCents = totalCents / passengerCount;
        double[] shares = new double[passengerCount];
        long distributed = 0;

        for (int i = 0; i < passengerCount; i++) {
            long cents = i == passengerCount - 1 ? totalCents - distributed : shareCents;
            shares[i] = cents / 100.0;
            distributed += cents;
        }

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter f1 = new FareSplitter("TRIP001", 100000, 3);
        FareSplitter f2 = new FareSplitter("TRIP003");
        System.out.println(java.util.Arrays.toString(f1.fareBreakdown()));
        System.out.println(java.util.Arrays.toString(f2.fareBreakdown()));
    }
}
