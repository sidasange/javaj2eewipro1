import java.util.Scanner;

public class TomAndJerry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter an integer: ");
        int number = sc.nextInt();
        
        if (number > 20 && number < 30) {
            if (number % 2 != 0) {
                System.out.println("Tom");
            } else {
                System.out.println("Jerry");
            }
        } else {
            System.out.println("The number does not meet the criteria.");
        }
        }}
        
    