
public class LibraryMember {
    private String membershipId;
    private String name;
    private String branchCode;
    private double finesOwed;
    private String displayName;
    private boolean premiumMember;
    private String securityAnswerHash;
    public LibraryMember() {
        this(null, null);
    }
    public LibraryMember(String name) {
        this(null, name);
    }
    public LibraryMember(String membershipId, String name) {
        this.name = name;
        this.displayName = name;
        setMembershipId(membershipId);
    }
    public String getMembershipId() {
        return membershipId;
    }
    public void setMembershipId(String id) {
        if (membershipId == null && id != null && !id.trim().isEmpty())
            membershipId = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        this.displayName = name;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public double getFinesOwed() {
        return finesOwed;
    }
    public void setFinesOwed(double finesOwed) {
        if (finesOwed >= 0)
            this.finesOwed = finesOwed;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public boolean isPremiumMember() {
        return premiumMember;
    }
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }
    public void setSecurityAnswer(String answer) {
        if (answer != null)
            securityAnswerHash = Integer.toHexString(answer.hashCode());
    }
    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());
        System.out.println(new LibraryMember("Priya Nair").getMembershipId());
    }
}
