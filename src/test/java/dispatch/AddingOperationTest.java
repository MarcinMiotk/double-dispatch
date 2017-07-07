package dispatch;

import org.junit.Test;

import static dispatch.IntegerUnit.of;
import static org.junit.Assert.*;

public class AddingOperationTest {

    @Test
    public void adding_account_and_int() {
       BankAccountUnit martin = new BankAccountUnit("Martin");
       IntegerUnit money = of(10);
       BankAccountUnit result = martin.add(money);
       assertEquals("Martin", result.getOwner());
       assertEquals("10", result.getBalance().toPlainString());
    }

    @Test
    public void adding_account_and_int_multiple_times() {
        BankAccountUnit martin = new BankAccountUnit("Martin");
        BankAccountUnit result = martin.add(of(10))
                .add(of(5))
                .add(of(20));
        assertEquals("Martin", result.getOwner());
        assertEquals("35", result.getBalance().toPlainString());
    }

    @Test
    public void adding_account_and_int_and_credit() {
        CreditCardUnit credit = new CreditCardUnit("Martin").add(of(100));
        BankAccountUnit martin = new BankAccountUnit("Martin").add(of(50));
        BankAccountUnit result = martin.add(credit);
        assertEquals("Martin", result.getOwner());
        assertEquals("150", result.getBalance().toPlainString());
    }

    @Test
    public void adding_credit_and_account_to_int() {
        CreditCardUnit credit = new CreditCardUnit("Martin").add(of(20));
        BankAccountUnit bank = new BankAccountUnit("Martin").add(of(30));
        IntegerUnit result = of(10).add(credit).add(bank);
        assertEquals(60, result.value.intValue());
    }

}