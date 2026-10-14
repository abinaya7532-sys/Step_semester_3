
public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty())
            throw new IllegalArgumentException("Student name is required");
        if (dishName == null || dishName.trim().isEmpty())
            throw new IllegalArgumentException("Dish name is required");
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    public void markDelivered() {
        if (delivered)
            System.out.println("Order already delivered");
        else {
            delivered = true;
            System.out.println("Order delivered successfully");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0, rejected = 0;

        for (String[] order : rawOrders) {
            try {
                if (order == null || order.length < 2)
                    throw new IllegalArgumentException();
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        processBatch(orders);
    }
}
