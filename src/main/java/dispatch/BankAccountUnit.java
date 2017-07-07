package dispatch;

import java.math.BigDecimal;

class BankAccountUnit implements AddingOperation<BankAccountUnit> {

    final String owner;
    final BigDecimal balance;

    public BankAccountUnit(String owner) {
        this.owner = owner;
        this.balance = BigDecimal.ZERO;
    }

    public BankAccountUnit(String owner, BigDecimal balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BankAccountUnit add(AddingOperation second) {
        return (BankAccountUnit)second.addConcrete(this);
    }

    public IntegerUnit addConcrete(IntegerUnit first) {
        return IntegerUnit.of(this.balance.toBigInteger().intValue()+first.value);
    }

    public BankAccountUnit addConcrete(BankAccountUnit first) {
        BigDecimal newBalance = first.getBalance().add(this.getBalance());
        return new BankAccountUnit(first.getOwner()+" added to "+this.getOwner(), newBalance);
    }

    public CreditCardUnit addConcrete(CreditCardUnit first) {
        BigDecimal newValue = first.getAmount().add(this.getBalance());
        return new CreditCardUnit(first.getAccount()+" added to "+this.getOwner(), newValue);
    }
}
