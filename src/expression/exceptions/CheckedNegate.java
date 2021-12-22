package expression.exceptions;

import expression.MyExpression;

public class CheckedNegate extends CheckedUnaryOperation {

    public CheckedNegate(MyExpression expression) {
        super(expression, "neg");
        //System.err.println("Negate: " + expression.getClass() + "; " + expression);
    }

    @Override
    protected int result(int a) {
//        System.err.println(a);
//        System.err.println("|||||||||||||||||||||||||");
        if (a <= Integer.MIN_VALUE) {
            throw new IntegerOutOfBoundsException();
        }
        return -a;
    }
}
