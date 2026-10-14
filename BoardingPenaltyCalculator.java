
public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (!Double.isFinite(minimumPenaltyPercent) || minimumPenaltyPercent < 0)
            throw new IllegalArgumentException("Invalid minimum penalty percentage");
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (!Double.isFinite(ticketFare) || ticketFare < 0 || minutesLate < 0)
            throw new IllegalArgumentException("Invalid fare or delay");

        if (minutesLate == 0)
            return 0.0;

        int firstTier = Math.min(minutesLate, 5);
        int secondTier = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTier = Math.max(minutesLate - 15, 0);

        double tieredPenalty = ticketFare * (
            firstTier * 0.005 +
            secondTier * 0.01 +
            thirdTier * 0.02
        );

        double minimumPenalty = ticketFare * minimumPenaltyPercent / 100.0;
        return Math.round(Math.max(tieredPenalty, minimumPenalty) * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(1.0);
        System.out.println(calculator.calculatePenalty(1000, 0));
        System.out.println(calculator.calculatePenalty(1000, 1));
        System.out.println(calculator.calculatePenalty(1000, 16));
    }
}
