import java.util.Scanner;

public class UniqueNumberChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int number = sc.nextInt();

        if (isUniqueNumberV1(number)) {
            System.out.println(number + " is a unique number.");
        } else {
            System.out.println(number + " is not a unique number.");
        }
    }

    static boolean isUniqueNumberV1(int num) {
        String strNum = String.valueOf(num);
        return strNum.chars().distinct().count() == strNum.length();
    }
}