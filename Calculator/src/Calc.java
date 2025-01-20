import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (например, 10 + 20:");
        String expression = scanner.nextLine();

        expression = expression.replace(" ", "");

        char operator = 0;
        int operatorIndex = -1;

        for (int i = 0; i <expression.length() ; i++) {
            char currentChar = expression.charAt(i);
            if(currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                operator = currentChar;
                operatorIndex = i;
                break;
            }
        }

        if(operatorIndex == -1) {
            System.out.println("Некорректный оператор");
        }

        try {
            double num1 = Double.parseDouble(expression.substring(0, operatorIndex));
            double num2 = Double.parseDouble(expression.substring(operatorIndex + 1));

            double res = performOperation(num1, num2, operator);
            System.out.printf("Результат: %.1f%n", res);
        }catch (NumberFormatException e) {
            System.out.println("Некорректный формат ввода");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static Double performOperation (double num1, double num2, char operator) {
        return switch (operator) {
          case '+' -> num1 + num2;
          case '-' -> num1 - num2;
          case '*' -> num1 * num2;
          case '/' -> {
              if(num2 == 0) {
                  throw new ArithmeticException("Деление на ноль невозможно");
              }
              yield num1 / num2;
          }
          default -> throw new IllegalArgumentException("Некорректный оператор");
        };
    }
}