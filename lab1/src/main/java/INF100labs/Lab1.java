package INF100labs;
import java.util.Scanner;

/**
 * Implement the methods task1, and task2.
 * These programming tasks was part of lab1 in INF100 fall 2022/2023.
 */
public class Lab1 {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        task1();
        task2();
    }
    
    public static void task1() {
        System.out.println("Hei, det er meg, datamaskinen.");
        System.out.println("Hyggelig å se deg her.");
        System.out.println("Lykke til med INF101!");
    }
    
    public static void task2() {
        sc = new Scanner(System.in); // Do not remove this line
        
        String name = readInput("Hva er ditt navn?");
        String address = readInput("Hva er din adresse?");
        String postInfo = readInput("Hva er ditt postnummer og poststed?");
        
        System.out.println("Kari Nordmanns adresse er:");
        System.out.println();
        System.out.println(name);
        System.out.println(address);
        System.out.println(postInfo);
    }
    
    /**
     * Reads input from console with given prompt
     * @param prompt
     * @return string input answer from user
     */
    private static String readInput(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
}