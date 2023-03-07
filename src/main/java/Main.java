import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.print("""
                ------------------------------------------------------------
                Обратите внимание: для конвертации валют используются курсы,
                предоставленные национальным банком Республики Беларусь.
                ------------------------------------------------------------
                Для выхода на любом из этапов введите "exit"
                ------------------------------------------------------------
                """);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("""
                    Пожалуйста, укажите номер валюты, которую вы хотите конвертировать.
                    1) BYN \t\t 2) USD
                    3) RUB \t\t 4) EUR
                    5) UAH \t\t 6) GBP
                    7) PLN \t\t 8) СHF
                    ------------------------------------------------------------
                    """);
            int firstNum;
            try {
                String temp = reader.readLine();
                if(temp.equals("exit")) return;
                firstNum = Integer.parseInt(temp);
            } catch (IOException | NumberFormatException e) {
                throw new RuntimeException("Пожалуйста, введите одно из предложенных чисел!");
            }
            RateService.requestByNumber(firstNum);
            System.out.print("""
                    ------------------------------------------------------------
                    Пожалуйста, укажите номер валюты, в которую вы хотите конвертировать.
                    1) BYN \t\t 2) USD
                    3) RUB \t\t 4) EUR
                    5) UAH \t\t 6) GBP
                    7) PLN \t\t 8) СHF
                    ------------------------------------------------------------
                    """);
            int secondNum;
            try {
                String temp = reader.readLine();
                if(temp.equals("exit")) return;
                secondNum = Integer.parseInt(temp);
                RateService.requestByNumber(secondNum);
            } catch (IOException | NumberFormatException e) {
                throw new RuntimeException("Пожалуйста, введите одно из предложенных чисел!");
            }
            String amountOfMoney;
            System.out.print("Введите сумму: ");
            while (!(amountOfMoney = reader.readLine()).equals("exit")) {
                try {
                    double quantity = Double.parseDouble(amountOfMoney);
                    System.out.printf("%.2f %s = %.2f %s\n", quantity, RateService.getCurrencyIsoNameByNumber(firstNum),
                            RateService.convert(quantity), RateService.getCurrencyIsoNameByNumber(secondNum));
                    System.out.print("Введите сумму: ");
                } catch (NumberFormatException e){
                    throw new RuntimeException("Пожалуйста, введите число в формате 123 или 12.34");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
