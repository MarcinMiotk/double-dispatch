package dispatch;

import java.math.BigDecimal;

class IntegerUnit implements AddingOperation<IntegerUnit> {

    final Integer value;

    IntegerUnit(int val) {
        this.value = val;
    }

    static IntegerUnit of(int v) {
        return new IntegerUnit(v);
    }

    public IntegerUnit add(AddingOperation second) {
       return (IntegerUnit)second.addConcrete(this);
    }

    public IntegerUnit addConcrete(IntegerUnit first) {
        return of(value+first.value);
    }

    public BankAccountUnit addConcrete(BankAccountUnit first) {
        BigDecimal newBalance = first.getBalance().add(new BigDecimal(value));
        return new BankAccountUnit(first.getOwner(), newBalance);
    }

    public CreditCardUnit addConcrete(CreditCardUnit first) {
        BigDecimal newBalance = first.getAmount().add(new BigDecimal(value));
        return new CreditCardUnit(first.getAccount(), newBalance);
    }
}
