
public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;
    public PatientProfile() {
        this(null, null);
    }
    public PatientProfile(String name) {
        this(null, name);
    }
    public PatientProfile(String patientId, String name) {
        this.name = name;
        setPatientId(patientId);
    }
    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String id) {
        if (patientId == null && id != null && !id.trim().isEmpty())
            patientId = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isDischarged() {
        return discharged;
    }
    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }
    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("[0-9]{4,6}"))
            lockerPinHash = Integer.toHexString(pin.hashCode());
    }
    public static void main(String[] args) {
        PatientProfile p = new PatientProfile();
        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");
        System.out.println(p.getPatientId());
        System.out.println(new PatientProfile("Arjun Iyer").getPatientId());
    }
}
