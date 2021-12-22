package expression.exceptions;

import expression.MyExpression;

public class CheckedSubtract extends CheckedAbstractOperation  {

    public CheckedSubtract(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2, "-");
    }

    @Override
    protected int result(int a, int b) {
        //System.err.println("Subtract: " + a + " and " + b);
        if (a <= 0 && b >= 0) {
            if (a < Integer.MIN_VALUE + b) {
                throw new IntegerOutOfBoundsException();
            }
        } else if (a >= 0 && b <= 0) {
            if (a > Integer.MAX_VALUE + b) {
                throw new IntegerOutOfBoundsException();
            }
        }
        return a - b;
    }
}
