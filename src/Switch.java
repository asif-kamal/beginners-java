import java.util.Scanner;

public class Switch {

    public static void switchExample(String letter) {

//        switch (letter) {
//            case "A":
//                System.out.println("Alpha");
//                break;
//            case "B":
//                System.out.println("Bravo");
//                break;
//            case "C":
//                System.out.println("Charlie");
//                break;
//            default:
//                System.out.println("no results found for " + letter);
//        }

        System.out.println(switch (letter) {
            case "A" -> "Alpha";
            case "B" -> {
                System.out.println("B is for Bravo");
                yield "Bravo";
            }
            case "C" -> "Charlie";
            default -> "sorry, still adding new letters to dictionary. " + letter + " not found.";
        });
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String letter = input.nextLine();// Read a character from user input

        switchExample(letter);
    }
}
