public class Conversion {

    public static double conversionToCentimeters(int heightInInches) {
        return heightInInches * 2.54;
    }

    public static double conversionToCentimeters(int heightInFeet, int heightInInches) {
        return conversionToCentimeters(heightInFeet * 12 + heightInInches);
    }

    public static void main(String[] args) {
        System.out.println((conversionToCentimeters(19)));
        System.out.println(conversionToCentimeters(5, 6));
    }

}
