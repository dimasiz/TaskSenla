public class BankAccount {
    private double balance;
    private int pinCode;
    private String cardNumber;

    public BankAccount(double balance, int pinCode, String cardNumber) {
        this.balance = balance;
        this.pinCode = pinCode;
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public double getBalance() {
        return balance;
    }

    public void petMoney(double amount) {
        balance += amount;
    }

    public boolean withdrawMoney(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
