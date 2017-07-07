package dispatch;

interface AddingOperation<T> {

    T add(AddingOperation second);

    <M extends AddingOperation> M addConcrete(IntegerUnit first);

    <M extends AddingOperation> M addConcrete(BankAccountUnit first);

    <M extends AddingOperation> M addConcrete(CreditCardUnit first);
}
