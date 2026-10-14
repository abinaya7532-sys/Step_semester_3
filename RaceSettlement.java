
public class RaceSettlement {
    public static void main(String[] args) {
        RaceEntry r = new RaceEntry("BIB5001", 50);
        r.pay(10, "UPI");
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.getBibCounter());
        RaceEntry[] entries = {
            new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500),
            null,
            new RelayTeamEntry("BIB4001", 300, 4)
        };
        System.out.println(RaceEntry.settleNight(entries));
    }
}
class RaceEntry {
    private static int counter;
    public final String entryCode;
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;
    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException();
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        entryCode = "RACE-" + (++counter);
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
        return Math.max(0, entryFee - amountPaid);
    }
    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5 || code.charAt(0) != 'M' || !Character.isUpperCase(code.charAt(4)))
            return false;
        for (int i = 1; i < 4; i++)
            if (!Character.isDigit(code.charAt(i)))
                return false;
        return true;
    }
    public static int getBibCounter() {
        return counter;
    }
    public static String settleNight(RaceEntry[] entries) {
        int processed = 0, skipped = 0, relays = 0, individuals = 0;
        for (RaceEntry entry : entries) {
            if (entry == null) {
                skipped++;
            } else {
                processed++;
                if (entry instanceof RelayTeamEntry)
                    relays++;
                else
                    individuals++;
            }
        }
        return processed + " processed | " + skipped + " null skipped | " + relays + " relay | " + individuals + " individual";
    }
}
class RunnerEntry extends RaceEntry {
    protected String category;
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}
class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;
    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }
}
class RelayTeamEntry extends RaceEntry {
    private int teamSize;
    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        if (teamSize <= 0)
            throw new IllegalArgumentException();
        this.teamSize = teamSize;
    }
}
