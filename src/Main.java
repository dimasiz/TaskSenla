import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final double MAX_WITHDRAWAL_AMOUNT = 50_000;

    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = FileManager.loadAccounts();
        System.out.println(accounts.size());
        ATM atm = new ATM(accounts, MAX_WITHDRAWAL_AMOUNT);
        Scanner scanner = new Scanner(System.in);

        boolean validCredentials = false;
        while (!validCredentials) {
            System.out.println("Введите номер карты (XXXX-XXXX-XXXX-XXXX):");
            String cardNumber = scanner.nextLine().replace("-", "");
            System.out.println("Введите PIN-код:");
            int pinCode = scanner.nextInt();
            scanner.nextLine();

            if (atm.checkBalance(cardNumber, pinCode)) {
                validCredentials = true;
                boolean flag = true;
                while (flag) {
                    System.out.println("Выберите операцию:\n1 - Снять\n2 - Пополнить\n3 - Выход");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.println("Введите сумму для снятия:");
                            double withdrawalAmount = scanner.nextDouble();
                            scanner.nextLine();
                            atm.withdrawMoney(cardNumber, pinCode, withdrawalAmount);
                            break;
                        case 2:
                            System.out.println("Введите сумму для пополнения:");
                            double depositAmount = scanner.nextDouble();
                            scanner.nextLine();
                            atm.petMoney(cardNumber, pinCode, depositAmount);
                            break;
                        case 3:
                            FileManager.saveAccounts(accounts);
                            System.out.println("Выход из программы.");
                            flag = false;
                            break;
                        default:
                            System.out.println("Неверный выбор, Попробуйте еще раз.");
                            break;
                    }
                }
            } else {
                System.out.println("Неверный номер карты или PIN-код,Попробуйте еще раз.");
            }
        }
    }
    }
