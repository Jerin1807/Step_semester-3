 import java.util.Scanner;

class InventoryBalancer {

    static void analyzeInventory(int[] sectionA, int[] sectionB) {

        int totalA = 0;
        int totalB = 0;

        // Find totals
        for (int i = 0; i < sectionA.length; i++) {
            totalA = totalA + sectionA[i];
            totalB = totalB + sectionB[i];
        }

        // Find highest quantity
        int highest = sectionA[0];
        String section = "Section A";
        int index = 0;

        for (int i = 0; i < sectionA.length; i++) {

            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "Section A";
                index = i;
            }

            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "Section B";
                index = i;
            }
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);

        if (totalA == totalB) {
            System.out.println("Status: Balanced");
        } else {
            System.out.println("Status: Not Balanced");
        }

        System.out.println("Highest Quantity: " + highest
                + " (" + section + ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] sectionA = new int[n];
        int[] sectionB = new int[n];

        System.out.println("Enter quantities for Section A:");
        for (int i = 0; i < n; i++) {
            sectionA[i] = sc.nextInt();
        }

        System.out.println("Enter quantities for Section B:");
        for (int i = 0; i < n; i++) {
            sectionB[i] = sc.nextInt();
        }

        analyzeInventory(sectionA, sectionB);
    }
}
