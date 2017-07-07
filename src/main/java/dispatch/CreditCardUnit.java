package dispatch;

import java.math.BigDecimal;

class CreditCardUnit implements AddingOperation<CreditCardUnit> {

    final String account;
    final BigDecimal amount;

    public CreditCardUnit(String account) {
        this.account = account;
        this.amount = BigDecimal.ZERO;
    }

    public CreditCardUnit(String account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CreditCardUnit add(AddingOperation second) {
        return (CreditCardUnit)second.addConcrete(this);
    }

    public IntegerUnit addConcrete(IntegerUnit first) {
        return IntegerUnit.of(this.amount.toBigInteger().intValue()+first.value);
    }

    public BankAccountUnit addConcrete(BankAccountUnit first) {
        BigDecimal newBalance = first.getBalance().add(this.getAmount());
        return new BankAccountUnit(first.getOwner(), newBalance);
    }

    public CreditCardUnit addConcrete(CreditCardUnit first) {
        BigDecimal newValue = first.getAmount().add(this.getAmount());
        return new CreditCardUnit(first.getAccount()+" added to "+this.getAccount(), newValue);
    }
}
