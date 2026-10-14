
import java.util.Arrays;
public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;
    private static String ledgerName;
    static {
        ledgerName = "MediTrack";
    }
    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || medicationCodes == null)
            throw new IllegalArgumentException();
        for (String code : medicationCodes) {
            if (code == null || code.length() != 5 || !code.startsWith("MED-") || !Character.isUpperCase(code.charAt(4)))
                throw new IllegalArgumentException("Invalid medication code");
        }
        this.patientId = patientId;
        this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
    }
    public String getPatientId() {
        return patientId;
    }
    public String[] getMedicationCodes() {
        return Arrays.copyOf(medicationCodes, medicationCodes.length);
    }
    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        String[] updated = getMedicationCodes();
        updated[index] = newCode;
        return new DischargeSummary(patientId, updated);
    }
    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0, skipped = 0, critical = 0, routine = 0;
        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                skipped++;
            } else {
                processed++;
                if (summary instanceof CriticalCareDischargeSummary)
                    critical++;
                else
                    routine++;
            }
        }
        return processed + " processed | " + skipped + " null skipped | " + critical + " critical-care | " + routine + " routine";
    }
    public static void main(String[] args) {
        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(summaries));
    }
}
class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;
    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        if (icuDays < 0)
            throw new IllegalArgumentException();
        this.icuDays = icuDays;
    }
    public int getIcuDays() {
        return icuDays;
    }
}
