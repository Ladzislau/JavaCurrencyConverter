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
                Note that currency conversion rates are provided by the
                National Bank of the Republic of Belarus.
                ------------------------------------------------------------
                To exit at any stage, type "exit"
                ------------------------------------------------------------
                """);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("""
                    Please enter the currency number you want to convert.
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
                throw new RuntimeException("Please enter one of the suggested numbers!");
            }
            RateService.requestByNumber(firstNum);
            System.out.print("""
                    ------------------------------------------------------------
                    Please enter the currency number you want to convert to.
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
                throw new RuntimeException("Please enter one of the suggested numbers!");
            }
            String amountOfMoney;
            System.out.print("Enter the amount: ");
            while (!(amountOfMoney = reader.readLine()).equals("exit")) {
                try {
                    double quantity = Double.parseDouble(amountOfMoney);
                    System.out.printf("%.2f %s = %.2f %s\n", quantity, RateService.getCurrencyIsoNameByNumber(firstNum),
                            RateService.convert(quantity), RateService.getCurrencyIsoNameByNumber(secondNum));
                    System.out.print("Enter the amount: ");
                } catch (NumberFormatException e){
                    throw new RuntimeException("Please enter a number in the format of 123 or 12.34");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
