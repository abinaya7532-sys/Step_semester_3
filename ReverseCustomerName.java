import java.util.Scanner;

public class ReverseCustomerName {
    static String reverseCustomerName(String customerName) {
        String reversed = "";
        for (int i = customerName.length() - 1; i >= 0; i--)
            reversed += customerName.charAt(i);
        return reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " + reverseCustomerName(name));
        sc.close();
    }
}