package INF100labs;

/**
 * Implement the methods multiplesOfSevenUpTo, multiplicationTable and crossSum.
 * These programming tasks was part of lab3 in INF100 fall 2022/2023.
 */
public class Lab3 {
    
    public static void main(String[] args) {
        // Call the methods here to test them on different inputs
    }

    public static void multiplesOfSevenUpTo(int n) {
        for (int i = 7; i <= n; i += 7) {
            System.out.println(i);
        }
    }

    public static void multiplicationTable(int n) {
        for (int i = 1; i <= n; i++) {
            StringBuilder row = new StringBuilder(i + ": ");
            for (int j = 1; j <= n; j++) {
                row.append(i * j).append(" ");
            }
            System.out.println(row.toString().trim());
        }
    }

    public static int crossSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}