import java.util.Scanner;

public class SumOfIntegers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter integers separated by spaces to add together. Type any non-integer to finish.");
        
        int result = addIntegers(scanner);
        
        System.out.println("Total: " + result);
        
        
    }
    
    private static int addIntegers(Scanner scanner) {
        int sum = 0;
        
        
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            } else {
                System.out.println("Error: Non-integer value entered. Exiting.");
                
                break;
            }
        }
        
        return sum;
    }
}
