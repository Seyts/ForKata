import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа арабских, либо два числа римских");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    // разделение введенной строки.
    public static String calc(String input) throws Exception {
        int fistNum;
        int secondNum;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = input.split("[+\\-*/]");
        oper = detectOperation(input);
        if (oper == null) throw new Exception("Cтрока не является математической операцией");
        if (operands.length != 2) throw new Exception("Максимум 2 числа!!!");




        // Если оба числа римские.
        if (isRoman(operands)) {
            fistNum = Convertor.convertToArabic(operands[0]);
            secondNum = Convertor.convertToArabic(operands[1]);
            isRoman = true;
        }
        // Если оба числа арабские.
        else {
            try {
                fistNum = Integer.parseInt(operands[0]);
                secondNum = Integer.parseInt(operands[1]);
                isRoman = false;
            } catch (NumberFormatException q) {
                throw new NumberFormatException("Вы ввели не цифры");
            }
        }

        if (((fistNum > 10) || (fistNum < 0)) || ((secondNum > 10) || (secondNum < 1))) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabic = calc(fistNum, secondNum, oper);
        if (isRoman) {
            if (arabic <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Convertor.covertToRoman(arabic);
        } else {
            result = String.valueOf(arabic);
        }
        return result;
    }


    static boolean isRoman(String[] operands) {
        return Convertor.isRoman(operands[0]) && Convertor.isRoman(operands[1]);
    }

    //    определение арифметического знака.
    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    //   Арифметическое действие.
    static int calc(int a, int b, String oper) {
        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}