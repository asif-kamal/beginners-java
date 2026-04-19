import java.util.Scanner;

public class ValidNumbers {

    public static int validateAndSumDigits() {
        int sum = 0;
        int count = 1;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter number #" + count + ": ");
                sum += input.nextInt();
                count++;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine(); // Clear the invalid input from the scanner buffer
            }
        } while (count <= 5);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(validateAndSumDigits());
    }
}
