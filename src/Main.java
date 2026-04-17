public class Main {
    public static void main(String[] args) {
        int myVariable = 50;

        System.out.println(
                myVariable == 0 ? "it's now 0" : "it's not 0"
        );

        if (myVariable == 0) System.out.println("It is 0"); else System.out.println("It is not 0");

        System.out.println(calculateScore(2, 5));

        char letter = 'B';

        switch (letter) {
            case 'A':
                System.out.println("Alpha");
                break;
            case 'B':
                System.out.println("Bravo");
                break;
            case 'C':
                System.out.println("Charlie");
                break;
            default:
                System.out.println("no results found.");
        }
    }

    public static String calculateScore(int num1, int num2) {
        return "Score is " + (num1 + num2);
    }
}