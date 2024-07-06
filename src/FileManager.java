import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String FILENAME = "bank_accounts.txt";
    private static final String SEPARATOR = ",";

    public static ArrayList<BankAccount> loadAccounts() {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts.length == 3) {
                    String cardNumber = parts[0];
                    int pinCode = Integer.parseInt(parts[1]);
                    double balance = Double.parseDouble(parts[2]);
                    accounts.add(new BankAccount(balance, pinCode, cardNumber));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return accounts;
    }

    public static void saveAccounts(ArrayList<BankAccount> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (BankAccount account : accounts) {
                writer.write(account.getCardNumber() + SEPARATOR + account.getPinCode() + SEPARATOR + account.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}