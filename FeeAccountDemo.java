class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
        else
            System.out.println("Invalid payment");
    }

    double getDue() {
        return Math.max(0, totalFee - amountPaid);
    }
}

class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        if (amount > 0) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid,
                          double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        if (scholarshipPercent < 0 || scholarshipPercent > 100)
            throw new IllegalArgumentException("Scholarship must be 0–100");
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}

public class FeeAccountDemo {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("101", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("102", 200000, 60000);
        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("103", 180000, 0, 20);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount account : accounts) {
            if (account instanceof ScholarshipFeeAccount)
                System.out.println("Scholarship account effective due: Rs " +
                        ((ScholarshipFeeAccount) account).effectiveDue());
            else if (account instanceof HostelFeeAccount)
                System.out.println("Hostel account due: Rs " + account.getDue());
            else
                System.out.println("Plain account due: Rs " + account.getDue());
        }
    }
}