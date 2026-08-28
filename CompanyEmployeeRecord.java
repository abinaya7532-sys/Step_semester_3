class Employee {
    double salary;
    Employee(double salary) {
        this.salary = salary;
    }
    double getSalary() {
        return salary;
    }
}
class ManagerEmployee extends Employee {
    double bonus;
    ManagerEmployee(double salary, double bonus) {
        super(salary);
        this.bonus = bonus;
    }
    double effectiveSalary() {
        return salary + bonus;
    }
}
class InternEmployee extends Employee {
    double cap;
    InternEmployee(double salary, double cap) {
        super(salary);
        this.cap = cap;
    }
    double effectiveSalary() {
        return Math.min(salary, cap);
    }
}
class ParkingSlot {
    String slotNo;
    ParkingSlot(String slotNo) {
        this.slotNo = slotNo;
    }
}
public class CompanyEmployeeRecord {
    String name;
    Employee employee;
    ParkingSlot slot;
    static int totalRecords = 0;
    CompanyEmployeeRecord(String name, Employee employee,ParkingSlot slot) {
        this.name = name;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }
    void fullProfile() {
        double pay;
        if (employee instanceof ManagerEmployee)
            pay = ((ManagerEmployee) employee).effectiveSalary();
        else if (employee instanceof InternEmployee)
            pay = ((InternEmployee) employee).effectiveSalary();
        else
            pay = employee.getSalary();
        if (slot != null)
            System.out.println(name + " | Pay: Rs " + pay + " | Slot: " + slot.slotNo);
        else
            System.out.println(name + " | Pay: Rs " + pay + " | Slot: no parking assigned");
    }
    public static void main(String[] args) {
        ManagerEmployee divya =new ManagerEmployee(70000, 8000);
        Employee karan =new Employee(40000);
        InternEmployee meera =new InternEmployee(12000, 10000);
        ParkingSlot a1 = new ParkingSlot("A1");
        ParkingSlot a2 = new ParkingSlot("A2");
        CompanyEmployeeRecord r1 =new CompanyEmployeeRecord("Divya", divya, a1);
        CompanyEmployeeRecord r2 =new CompanyEmployeeRecord("Karan", karan, a2);
        CompanyEmployeeRecord r3 =new CompanyEmployeeRecord("Meera", meera, null);
        r1.fullProfile();
        r2.fullProfile();
        r3.fullProfile();
        System.out.println("Total records: " + totalRecords);
    }
}