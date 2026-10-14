
public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (!Double.isFinite(minimumSurgePercent) || minimumSurgePercent < 0)
            throw new IllegalArgumentException("Invalid minimum surge percentage");
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (!Double.isFinite(orderValue) || orderValue < 0 || delayMinutes < 0)
            throw new IllegalArgumentException("Invalid order value or delay");

        if (delayMinutes == 0)
            return 0.0;

        int first = Math.min(delayMinutes, 5);
        int second = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int third = Math.max(delayMinutes - 15, 0);

        double tieredFee = orderValue * (
            first * 0.005 +
            second * 0.01 +
            third * 0.02
        );

        double minimumFee = orderValue * minimumSurgePercent / 100.0;
        return Math.round(Math.max(tieredFee, minimumFee) * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);
        System.out.println(calculator.calculateSurgeFee(500, 0));
        System.out.println(calculator.calculateSurgeFee(500, 1));
        System.out.println(calculator.calculateSurgeFee(500, 16));
    }
}
