public class InventoryBalancer {
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA.length != sectionB.length) {
            System.out.println("Arrays must have equal lengths");
            return;
        }

        int totalA = 0, totalB = 0;
        int highest = Integer.MIN_VALUE;
        String section = "";
        int item = 0;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];

            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "Section A";
                item = i + 1;
            }

            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "Section B";
                item = i + 1;
            }
        }

        String status = totalA == totalB ? "Balanced" : "Not Balanced";

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);
        System.out.println("Status: " + status);

        if (highest == Integer.MIN_VALUE)
            System.out.println("No Inventory Items");
        else
            System.out.println("Highest Quantity: " + highest +
                    " (" + section + ", Item " + item + ")");
    }

    public static void main(String[] args) {
        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        analyzeInventory(sectionA, sectionB);
    }
}