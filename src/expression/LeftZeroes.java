package expression;

public class LeftZeroes extends UnaryOperation {
    public LeftZeroes(MyExpression expression) {
        super(expression, "l0");
    }

    @Override
    protected int result(int a) {
        return Integer.numberOfLeadingZeros(a);
    }
}
