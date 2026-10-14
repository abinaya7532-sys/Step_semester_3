
import java.util.*;

interface CreditPolicy {
    int getLimit();
}

class RegularPolicy implements CreditPolicy {
    public int getLimit() {
        return 24;
    }
}

class HonorsPolicy implements CreditPolicy {
    public int getLimit() {
        return 28;
    }
}

class ExchangePolicy implements CreditPolicy {
    public int getLimit() {
        return 20;
    }
}

class Student {
    String name;
    int credits;
    CreditPolicy policy;

    Student(String name, int credits, CreditPolicy policy) {
        this.name = name;
        this.credits = credits;
        this.policy = policy;
    }
}

class Elective {
    String name;
    int credits, capacity;
    List<Student> enrolled = new ArrayList<>();
    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean contains(Student s) {
        return enrolled.contains(s) || waitlist.contains(s);
    }

    boolean enroll(Student s) {
        if (contains(s)) {
            System.out.println(s.name + " is already enrolled or waitlisted.");
            return false;
        }

        if (s.credits + credits > s.policy.getLimit()) {
            System.out.println("Enrollment failed: " + s.name +
                    " would exceed the credit limit (" +
                    (s.credits + credits) + "/" + s.policy.getLimit() + ").");
            return false;
        }

        if (enrolled.size() == capacity) {
            waitlist.offer(s);
            System.out.println(name + " is full.");
            System.out.println(s.name + " added to waitlist (position " +
                    waitlist.size() + ").");
            return false;
        }

        enrolled.add(s);
        s.credits += credits;
        System.out.println(s.name + " enrolled in " + name +
                " (credits: " + s.credits + "/" + s.policy.getLimit() + ").");
        return true;
    }

    void drop(Student s) {
        if (!enrolled.remove(s)) {
            System.out.println(s.name + " is not enrolled.");
            return;
        }

        s.credits -= credits;
        System.out.println(s.name + " dropped " + name +
                " (credits: " + s.credits + "/" + s.policy.getLimit() + ").");

        int size = waitlist.size();
        for (int i = 0; i < size; i++) {
            Student next = waitlist.poll();

            if (next.credits + credits <= next.policy.getLimit()) {
                enrolled.add(next);
                next.credits += credits;
                System.out.println(next.name +
                        " promoted from waitlist and enrolled in " + name +
                        " (credits: " + next.credits + "/" +
                        next.policy.getLimit() + ").");
                break;
            } else {
                System.out.println(next.name + " skipped: credit limit exceeded.");
                waitlist.offer(next);
            }
        }
    }
}

public class ElectiveEnrollment {
    public static void main(String[] args) {
        Elective e = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", 20, new RegularPolicy());
        Student ravi = new Student("Ravi", 22, new HonorsPolicy());
        Student neha = new Student("Neha", 12, new ExchangePolicy());
        Student kiran = new Student("Kiran", 22, new RegularPolicy());

        e.enroll(asha);
        e.enroll(ravi);
        e.enroll(neha);
        e.enroll(kiran);
        e.drop(asha);
    }
}
