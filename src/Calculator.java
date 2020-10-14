import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter numbers with spaces:");
        System.out.println("Input:");

        String string = scanner.nextLine();
        String[] parts = string.split(" ");
        int result1 = 0;
        int result2 = 0;


        try {
            if (Character.isDigit(string.charAt(0))) {
                for (int i = 0; i < parts.length; i++) {


                    if (Integer.parseInt(parts[0]) >= 1 && Integer.parseInt(parts[2]) <= 10) {
                        switch (parts[1]) {
                            case "+":
                                result1 = Integer.parseInt(String.valueOf(parts[0])) +
                                        Integer.parseInt(String.valueOf(parts[2]));
                                break;
                            case "-":
                                result1 = Integer.parseInt(String.valueOf(parts[0])) -
                                        Integer.parseInt(String.valueOf(parts[2]));
                                break;
                            case "*":
                                result1 = Integer.parseInt(String.valueOf(parts[0])) *
                                        Integer.parseInt(String.valueOf(parts[2]));
                                break;
                            case "/":
                                result1 = Integer.parseInt(String.valueOf(parts[0])) /
                                        Integer.parseInt(String.valueOf(parts[2]));
                                break;
                            default:
                                System.out.println("Invalid operator!");
                                System.exit(0);
                        }
                        System.out.println("Output:");
                        System.out.println(result1);
                        break;
                    }
                }


            } else {
                if (romanToArabic(parts[0]) >= 1 && romanToArabic(parts[2]) <= 10) {
                    switch (parts[1]) {
                        case "+":
                            result2 = romanToArabic(parts[0]) +
                                    romanToArabic(parts[2]);
                            break;
                        case "-":
                            result2 = romanToArabic(parts[0]) -
                                    romanToArabic(parts[2]);
                            break;
                        case "*":
                            result2 = romanToArabic(parts[0]) *
                                    romanToArabic(parts[2]);
                            break;
                        case "/":
                            result2 = romanToArabic(parts[0]) /
                                    romanToArabic(parts[2]);
                            break;
                        default:
                            System.out.println("Invalid operator!");
                            System.exit(0);
                    }
                    System.out.println("Output:");
                    System.out.println(arabicToRoman(result2));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            System.exit(0);
        }
    }

    public static String arabicToRoman(int number) {

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        return result;
    }
}

enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10);

    private final int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}

