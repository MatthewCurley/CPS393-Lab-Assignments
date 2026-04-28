package bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    void testDeposit() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testWithdraw() {
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance());
    }

    @Test
    void testInvalidDeposit() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10.0);
        });
    }

    @Test
    void testOverdraft() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(200.0);
        });
    }

    @Test
    void testNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(-50.0);
        });
    }

    @Test
    void testTransfer() {
    BankAccount secondAccount = new BankAccount(50.0);

    account.transfer(secondAccount, 30.0);

    assertEquals(70.0, account.getBalance());
    assertEquals(80.0, secondAccount.getBalance());
}
}