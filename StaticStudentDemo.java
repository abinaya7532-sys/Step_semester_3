class BrokenStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(String name, String regNo, int attendance) {
        BrokenStudent.name = name;
        BrokenStudent.regNo = regNo;
        BrokenStudent.attendance = attendance;
    }
}

class CorrectStudent {
    String name;
    String regNo;
    int attendance;
    static String university = "SRM";
    static int admissionCount = 0;

    CorrectStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class StaticStudentDemo {
    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenStudent s1 = new BrokenStudent("Ravi", "101", 82);
        BrokenStudent s2 = new BrokenStudent("Meera", "102", 74);
        System.out.println(s1.name);
        System.out.println(s2.name);

        // name, regNo, and attendance must not be static because each student
        // needs separate values. Static fields are shared by every object.

        System.out.println("Fixed version:");
        CorrectStudent a = new CorrectStudent("Ravi", 82);
        CorrectStudent b = new CorrectStudent("Meera", 74);
        a.printIdCard();
        b.printIdCard();
        CorrectStudent.printTotalAdmissions();
    }
}