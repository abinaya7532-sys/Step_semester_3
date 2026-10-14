
public class BusTicketAccount {
    private final String bookingId;
    private final double ticketFare;
    private static final BoardingPenaltyCalculator CALCULATOR;

    static {
        CALCULATOR = new BoardingPenaltyCalculator(1.0);
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid booking ID");
        if (!Double.isFinite(ticketFare) || ticketFare < 0)
            throw new IllegalArgumentException("Invalid ticket fare");
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        return CALCULATOR.calculatePenalty(ticketFare, minutesLate);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null)
            return;
        if (!Double.isFinite(amount) || amount < 0)
            throw new IllegalArgumentException("Invalid reconciliation amount");

        double penalty = account.calculatePenalty(minutesLate);

        if (account instanceof SleeperBusTicketAccount)
            penalty *= 0.90;

        System.out.printf("%s | Amount: %.2f | Penalty: %.2f%n",
            account.getBookingId(), amount, penalty);
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null ||
            accounts.length != amounts.length || accounts.length != minutesLateArray.length)
            throw new IllegalArgumentException("All arrays must have matching lengths");

        int processed = 0, nullSkipped = 0, sleeperCount = 0, regularCount = 0;
        double totalPenalties = 0.0;
        BusTicketAccount processor = new BusTicketAccount("PROCESSOR");

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            if (!Double.isFinite(amounts[i]) || amounts[i] < 0 || minutesLateArray[i] < 0)
                continue;

            double penalty = account.calculatePenalty(minutesLateArray[i]);

            if (account instanceof SleeperBusTicketAccount) {
                penalty *= 0.90;
                sleeperCount++;
            } else {
                regularCount++;
            }

            processor.processAccount(account, amounts[i], minutesLateArray[i]);
            totalPenalties += penalty;
            processed++;
        }

        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = %.2f%n",
            processed, nullSkipped, sleeperCount, regularCount, totalPenalties);
    }
}

class SleeperBusTicketAccount extends BusTicketAccount {
    public SleeperBusTicketAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public SleeperBusTicketAccount(String bookingId) {
        super(bookingId);
    }
}
