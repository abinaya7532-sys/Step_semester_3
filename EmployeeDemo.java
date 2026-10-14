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

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(String id, String name, double salary, double stipendCap) {
        super(id, name, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E1", "Ravi", 40000),
            new ManagerEmployee("E2", "Divya", 70000, 8000),
            new InternEmployee("E3", "Meera", 12000, 10000)
        };

        for (Employee e : employees) {
            if (e instanceof ManagerEmployee)
                System.out.println("Manager effective pay: Rs " +
                        ((ManagerEmployee) e).effectiveSalary());
            else if (e instanceof InternEmployee)
                System.out.println("Intern effective pay: Rs " +
                        ((InternEmployee) e).effectiveSalary());
            else
                System.out.println("Plain employee pay: Rs " + e.getSalary());
        }
    }
}