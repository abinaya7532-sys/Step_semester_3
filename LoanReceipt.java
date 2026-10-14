
import java.util.Arrays;
public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;
    private static String ledgerName;
    static {
        ledgerName = "PageTurner";
    }
    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || bookIds == null)
            throw new IllegalArgumentException();
        for (String id : bookIds) {
            if (!isValidBookId(id))
                throw new IllegalArgumentException("Invalid book ID");
        }
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }
    private static boolean isValidBookId(String id) {
        if (id == null || id.length() != 6 || !id.startsWith("BK-"))
            return false;
        for (int i = 3; i < 6; i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9')
                return false;
        }
        return true;
    }
    public String getMemberId() {
        return memberId;
    }
    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updated = getBookIds();
        updated[index] = newId;
        return new LoanReceipt(memberId, updated);
    }
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0, skipped = 0, referenceOnly = 0, regular = 0;
        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                skipped++;
            } else {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt)
                    referenceOnly++;
                else
                    regular++;
            }
        }
        return processed + " processed | " + skipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }
    public static void main(String[] args) {
        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(receipts));
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);
    }
}
class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;
    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        if (roomNumber == null || roomNumber.trim().isEmpty())
            throw new IllegalArgumentException();
        this.roomNumber = roomNumber;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
}
