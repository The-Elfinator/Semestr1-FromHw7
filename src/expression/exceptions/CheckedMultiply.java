package expression.exceptions;

import expression.MyExpression;

public class CheckedMultiply extends CheckedAbstractOperation {

    public CheckedMultiply(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2, "*");
    }

    @Override
    protected int result(int a, int b) {
//        if (a != 0 && b != 0) {
//            if (a > 0 && b > 0) {
//                if (a > Integer.MAX_VALUE / b + Integer.MAX_VALUE % b) {
//                    throw new IntegerOutOfBoundsException();
//                }
//            } else if (a < 0 && b < 0) {
//                if (a < Integer.MAX_VALUE / b + Integer.MAX_VALUE % b) {
//                    throw new IntegerOutOfBoundsException();
//                }
//            } else if (a < 0) {
//                if (a < Integer.MIN_VALUE / b + Integer.MIN_VALUE % b) {
//                    throw new IntegerOutOfBoundsException();
//                }
//            } else {
//                if (b < Integer.MIN_VALUE / a + Integer.MIN_VALUE % a) {
//                    throw new IntegerOutOfBoundsException();
//                }
//            }
//        }
        //System.err.println("Multiply: " + a + " and " + b);
        if (a != 0 && b != 0) {
            if (a < 0 && b > 0) {
                if (a < Integer.MIN_VALUE / b) {
                    throw new IntegerOutOfBoundsException();
                }
            } else if (a < 0 && b < 0) {
                if (a < Integer.MAX_VALUE / b) {
                    throw new IntegerOutOfBoundsException();
                }
            } else if (a > 0 && b < 0) {
                if (b < Integer.MIN_VALUE / a) {
                    throw new IntegerOutOfBoundsException();
                }
            } else if (a > 0 && b > 0) {
                if (a > Integer.MAX_VALUE / b) {
                    throw new IntegerOutOfBoundsException();
                }
            }
        }
        return a * b;
    }
}
