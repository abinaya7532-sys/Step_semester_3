
import java.util.Arrays;
public class PatientVitals {
    private double[] readings = new double[500];
    private int count;
    public PatientVitals(double[] initialReadings) {
        if (initialReadings != null) {
            for (double reading : initialReadings)
                recordReading(reading);
        }
    }
    public void recordReading(double reading) {
        if (reading > 0 && reading <= 45 && count < readings.length)
            readings[count++] = reading;
    }
    public double getAverage() {
        if (count == 0)
            return 0;
        double total = 0;
        for (int i = 0; i < count; i++)
            total += readings[i];
        return total / count;
    }
    public double[] getAllReadings() {
        return Arrays.copyOf(readings, count);
    }
    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        System.out.println(Arrays.toString(v.getAllReadings()));
        System.out.println(v.getAverage());
    }
}
