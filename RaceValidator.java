
public class RaceValidator {
    public static void main(String[] args) {
        String[] bibs = {"BIB1", "B1", "BIB2"};
        System.out.println(RaceEntry.registerBatch(bibs, 80));
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println(r.getBalanceDue());
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
    public void announce() {
        System.out.println("Race Entry | Balance: " + getBalanceDue());
    }
    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0, rejected = 0;
        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}
class RunnerEntry extends RaceEntry {
    protected String category;
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
    @Override
    public void announce() {
        System.out.println("Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + getBalanceDue());
    }
}
