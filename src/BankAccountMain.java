public class BankAccountMain {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("122H3EX009");
        bankAccount.setBalance(500);
        bankAccount.setCustomerName("John Smith");
        bankAccount.setEmail("jsmith@email.com");
        bankAccount.setPhoneNumber("5559995678");

        System.out.println(bankAccount.withdraw(550));
        System.out.println(bankAccount.deposit(450));

    }
}
