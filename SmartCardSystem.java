
import java.util.*;

interface PricingPlan {
    double calculatePrice(double price);
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price;
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.9;
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.8;
    }
}

class Transaction {
    String description;
    double amount;

    Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }
}

class SmartCard {
    private String id;
    private PricingPlan plan;
    private double balance;
    private boolean blocked;
    private List<Transaction> transactions = new ArrayList<>();
    private Set<String> refunded = new HashSet<>();

    SmartCard(String id, PricingPlan plan) {
        this.id = id;
        this.plan = plan;
    }

    void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100 || balance + amount > 5000) {
            System.out.println("Top-up rejected: Amount must be at least ₹100 and balance cannot exceed ₹5000.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction("Top-up", amount));
        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                id, amount, balance);
    }

    void purchase(String item, double price) {
        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return;
        }

        double charged = plan.calculatePrice(price);

        if (charged > balance) {
            System.out.printf("Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    charged, balance);
            return;
        }

        balance -= charged;
        transactions.add(new Transaction(item, -charged));
        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                item, charged, balance);
    }

    void refund(String item, double amount) {
        if (refunded.contains(item)) {
            System.out.println("Refund rejected: " + item + " has already been refunded.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction(item + " refund", amount));
        refunded.add(item);
        System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                amount, item, balance);
    }

    void block() {
        blocked = true;
    }

    void unblock() {
        blocked = false;
    }

    void statement() {
        System.out.print("Mini-statement for " + id + ": ");
        double total = 0;

        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            total += t.amount;
            System.out.printf("%s%.2f", t.amount >= 0 ? "+" : "", t.amount);

            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(" = ₹%.2f.%n", total);
    }
}

public class SmartCardSystem {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500);
        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Large Order", 400);
        card.refund("Veg Thali", 108);
        card.refund("Veg Thali", 108);
        card.statement();
    }
}
