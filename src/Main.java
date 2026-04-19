import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int myVariable = 50;

        System.out.println(
                myVariable == 0 ? "it's now 0" : "it's not 0"
        );

        if (myVariable == 0) System.out.println("It is 0"); else System.out.println("It is not 0");

        System.out.println(calculateScore(2, 5));


    }

    public static String calculateScore(int num1, int num2) {
        return "Score is " + (num1 + num2);
    }
}