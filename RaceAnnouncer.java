
public class RaceAnnouncer {
    public static String announceAll(RaceEntry[] entries) {
        StringBuilder report = new StringBuilder();
        for (RaceEntry entry : entries) {
            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                report.append("Relay Team | Bib: ").append(relay.getBibNumber()).append(" | Team Size: ").append(relay.getTeamSize()).append(" | Balance: ").append(relay.getBalanceDue()).append(" [Team size via downcast: ").append(relay.getTeamSize()).append("] | ");
            } else {
                report.append(entry.getAnnouncement()).append(" | ");
            }
        }
        return report.toString();
    }
    public static void main(String[] args) {
        RaceEntry[] entries = {
            new RunnerEntry("BIB2001", 80, "Open 10K"),
            new RelayTeamEntry("BIB4001", 300, 4)
        };
        System.out.println(announceAll(entries));
    }
}
class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;
    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException();
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }
    public void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }
    public double getBalanceDue() {
        return Math.max(0, entryFee - amountPaid);
    }
    public String getBibNumber() {
        return bibNumber;
    }
    public String getAnnouncement() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }
}
class RunnerEntry extends RaceEntry {
    private String category;
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
    @Override
    public String getAnnouncement() {
        return "Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}
class RelayTeamEntry extends RaceEntry {
    private int teamSize;
    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
    public int getTeamSize() {
        return teamSize;
    }
    @Override
    public String getAnnouncement() {
        return "Relay Team | Bib: " + bibNumber + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
    }
}
