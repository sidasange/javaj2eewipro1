import java.util.Scanner;

public class RearrangeArrayElements {
    public static void main(String[] args) {
        new RearrangeArrayElements().inputAcceptor();
    }

    // Write logic to get inputs from user and send inputs for validation
    public void inputAcceptor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int size = sc.nextInt();

        if (!inputArraySizeValidator(size)) {
            displayResult(null);
            return;
        }

        int[] inputArray = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            inputArray[i] = sc.nextInt();
        }

        if (!inputArrayValidator(inputArray)) {
            displayResult(null);
            return;
        }

        int[] rearrangedArray = computeRearrangedArray(inputArray);
        displayResult(rearrangedArray);
    }

    // Write logic to validate the given array size is valid or not
  
  public boolean inputArraySizeValidator(int size) {
        return size > 0;
    }

    // Write logic to validate the given input array is sorted or not
  
  public boolean inputArrayValidator(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1]) {
                return false; // Array is not in ascending order
            }
        }
        return true;
    }

    // Write logic to rearrange elements of array and return the result array
   
 public int[] computeRearrangedArray(int[] inputArray) {
        int[] result = new int[inputArray.length];
        int start = 0, end = inputArray.length - 1;
        boolean toggle = true; // This will help us switch between selecting from the end or start of the array.

        for (int i = 0; i < inputArray.length; i++) {
            if (toggle) {
                result[i] = inputArray[end--];
            } else {
                result[i] = inputArray[start++];
            }
            toggle = !toggle; // Switch the toggle
        }
        return result;
    }

    // Write logic to print the result
    public void displayResult(int[] outputArray) {
        if (outputArray == null) {
            System.out.println("Give proper input");
        } else {
            for (int i = 0; i < outputArray.length; i++) {
                System.out.print(outputArray[i] + " ");
            }
            System.out.println();
        }
    }
}
