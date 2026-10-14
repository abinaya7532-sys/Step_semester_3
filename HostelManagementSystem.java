class StudentFeeAccount {
    private double totalFee;
    private double amountPaid;

    StudentFeeAccount(double totalFee, double amountPaid) {
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

class StudentHostelFee extends StudentFeeAccount {
    StudentHostelFee(double totalFee, double amountPaid) {
        super(totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        if (amount > 0) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot() {
        if (occupied < beds)
            occupied++;
    }
}

class SrmStudent {
    String name;
    String regNo;
    StudentHostelFee feeAccount;
    HostelRoom room;
    static int totalStudents = 0;

    SrmStudent(String name, String regNo, StudentHostelFee feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        totalStudents++;
    }

    String fullStatus() {
        String roomNo = room == null ? "unallotted" : room.roomNo;
        return name + " | Due: Rs " + feeAccount.getDue() +
                " | Room: " + roomNo;
    }
}

public class HostelManagementSystem {
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null)
            return null;
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds)
                return room;
        }
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, SrmStudent student) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room == null) {
            System.out.println("No rooms available for " + student.name);
            return;
        }
        room.allot();
        student.room = room;
    }

    public static void main(String[] args) {
        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        SrmStudent s1 = new SrmStudent("Ravi", "101",
                new StudentHostelFee(200000, 60000));
        SrmStudent s2 = new SrmStudent("Anitha", "102",
                new StudentHostelFee(180000, 0));
        SrmStudent s3 = new SrmStudent("Karthik", "103",
                new StudentHostelFee(200000, 0));

        s1.feeAccount.pay(20000);
        s2.feeAccount.pay(-500);

        safeAllot(rooms, s1);
        safeAllot(rooms, s2);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}