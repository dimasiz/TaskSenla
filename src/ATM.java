import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private ArrayList<BankAccount> accounts;
    private double maximumWithdrawalAmount;

    public ATM(ArrayList<BankAccount> accounts, double maxWithdrawalAmount) {
        this.accounts = accounts;
        this.maximumWithdrawalAmount = maxWithdrawalAmount;
    }

    public boolean checkBalance(String cardNumber, int pinCode) {
        BankAccount account = getAccount(cardNumber, pinCode);
        if (account != null) {
            System.out.println("Баланс Счета: " + account.getBalance());
            return true;
        }
        System.out.println("Неверный номер карты или PIN-код");
        return false;
    }

    public boolean withdrawMoney(String cardNumber, int pinCode, double amount) {
        BankAccount account = getAccount(cardNumber, pinCode);
        if (account != null) {
            if (amount <= account.getBalance() && amount <= maximumWithdrawalAmount) {
                if (account.withdrawMoney(amount)) {
                    System.out.println("Успешно снято:" + amount);
                    return true;
                } else {
                    System.out.println("Недостаточно средств на счете");
                }
            } else {
                System.out.println("Превышен лимит снятия");
            }
        } else {
            System.out.println("Неверный номер карты или PIN-код");
        }
        return false;
    }

    public boolean petMoney(String cardNumber, int pinCode, double amount) {
        BankAccount account = getAccount(cardNumber, pinCode);
        if (account != null) {
            if (amount <= 1_000_000) {
                account.petMoney(amount);
                System.out.println("Успешно внесено:" + amount);
                return true;
            } else {
                System.out.println("Превышен лимит пополнения");
            }
        } else {
            System.out.println("Неверный номер карты или PIN-код");
        }
        return false;
    }

    private BankAccount getAccount(String cardNumber, int pinCode) {
        for (BankAccount account : accounts) {
            if (account.getCardNumber().equals(cardNumber)  && account.getPinCode() == pinCode) {
                return account;
            }
        }
        return null;
    }
}