package expression.exceptions;

import expression.MyExpression;

public class CheckedDivide extends CheckedAbstractOperation {
    public CheckedDivide(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2, "/");
    }

    @Override
    protected int result(int a, int b) {
        //System.err.println("Divide: " + a + " and " + b);
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new IntegerOutOfBoundsException();
        }
        if (b == 0) {
            throw new DivideByZeroException();
        }
        return a / b;
    }

}
