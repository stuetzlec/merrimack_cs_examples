package csc2620_unit3_14_practice;


/**
 * A Bank can hold up to a fixed number of BankAccounts.
 * @author cs302
 */
public class Bank {
    
    /** The array of BankAccount objects. */
    private final BankAccount[] accounts;

    /** The first available account index. */
    private int firstAvailableAcc;

    /**
     * Creates a bank that can have up to numAccounts accounts.
     * @param numAccounts Number of accounts to open the bank with capacity for
     */
    public Bank(int numAccounts) {
        firstAvailableAcc = 0;
        accounts = new BankAccount[ numAccounts ];
    }

    /**
     * Adds the given BankAccount to the bank. If the bank is full
     * an error message is printed and the bank is unchanged.
     * @param account The account to add
     */
    public void add(BankAccount account) {
        accounts[firstAvailableAcc++ ] = account;
    }

    /**
     * Returns the bank account with the given account number.
     * If no such account exists, null is returned.
     * @param acctNumber The account number
     * @return The account
     */
    public BankAccount find(int acctNumber) {
        for( BankAccount b : accounts )
        {
            if( b.getAccountNumber() == acctNumber )
                return b;
        }
        return null;
    }

    /**
     * Returns a string representation of the bank.
     * The format is one account per line.
     * @return All of the accounts, numbers and balances
     */
    @Override
    public String toString() {
        if (firstAvailableAcc == 0) return "NONE";
        
        String result = "";
        for (int i = 0; i < firstAvailableAcc; i++) {
            result += accounts[i].toString() + "\n";
        }

        return result;
    }
    
    /**
     * Get the number of accounts in the bank
     * @return The number of accounts held by the bank
     */
    public int getNumAccounts() {
        return firstAvailableAcc;
    }
}
