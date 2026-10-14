class Employee {
    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(String id, String name, double salary, double teamBonus) {
        super(id, name, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity)
            occupiedCount++;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;
    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId, Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        totalRecords++;
    }

    String fullProfile() {
        double pay = employee instanceof ManagerEmployee
                ? ((ManagerEmployee) employee).effectiveSalary()
                : employee.getSalary();

        String slotNo = slot == null ? "no parking assigned" : slot.slotNo;

        return name + " | Pay: Rs " + pay + " | Slot: " + slotNo;
    }
}

public class CompanyParkingSystem {
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null)
            return null;

        for (ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity)
                return slot;
        }
        return null;
    }

    static void safeAllot(ParkingSlot[] slots, CompanyEmployeeRecord record) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot != null) {
            slot.allot(record.empId);
            record.slot = slot;
        }
    }

    public static void main(String[] args) {
        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        CompanyEmployeeRecord e1 = new CompanyEmployeeRecord("Divya", "E1",
                new ManagerEmployee("E1", "Divya", 70000, 8000));
        CompanyEmployeeRecord e2 = new CompanyEmployeeRecord("Karan", "E2",
                new Employee("E2", "Karan", 40000));
        CompanyEmployeeRecord e3 = new CompanyEmployeeRecord("Meera", "E3",
                new InternEmployeeForParking("E3", "Meera", 12000, 10000));

        safeAllot(slots, e1);
        safeAllot(slots, e2);

        System.out.println(e1.fullProfile());
        System.out.println(e2.fullProfile());
        System.out.println(e3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}

class InternEmployeeForParking extends Employee {
    private double stipendCap;

    InternEmployeeForParking(String id, String name, double salary, double stipendCap) {
        super(id, name, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}