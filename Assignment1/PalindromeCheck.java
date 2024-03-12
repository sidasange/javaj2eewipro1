import java.util.Scanner;

public class PalindromeCheck{
    public static void main(String[] args) {
        // Create a Scanner object to get input from the user
        Scanner scanner = new Scanner(System.in);

        // Get input from the user
        System.out.print("Enter a number: ");
        long number;
            number = scanner.nextLong();

        // Check if the number is a palindrome
        if (isPalindrome(number)) {
            System.out.println(number + " is palindrome");

            // Check the sum of even digits
            long evenSum = sumOfEvenDigits(number);
            if (evenSum > 25) {
                System.out.println("Sum of even numbers is greater than 25");
            } else {
                System.out.println("Sum of even numbers is less than or equal to 25");
            }
        } else {
            System.out.println(number + " is not palindrome");
        }

        // Close the Scanner
        scanner.close();
    }

    // Method to check if a number is a palindrome
    private static boolean isPalindrome(long num) {
        long originalNum = num;
        long reverseNum = 0;

        while (num > 0) {
            long digit = num % 10;
            reverseNum = reverseNum * 10 + digit;
            num /= 10;
        }

        return originalNum == reverseNum;
    }

    // Method to calculate the sum of even digits in a number
    private static long sumOfEvenDigits(long num) {
        long sum = 0;

        while (num > 0) {
            long digit = num % 10;
            if (digit % 2 == 0) {
                sum += digit;
            }
            num /= 10;
        }

        return sum;
    }
}