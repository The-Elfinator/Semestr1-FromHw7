package expression.exceptions;

import expression.MyExpression;
import expression.Operation;

public abstract class CheckedAbstractOperation extends Operation {
    public CheckedAbstractOperation(MyExpression expression1, MyExpression expression2, String tag) {
        super(expression1, expression2, tag);
    }

    @Override
    protected abstract int result(int a, int b);
}
