package expression;

public class RightZeroes extends UnaryOperation {
    public RightZeroes(MyExpression expression) {
        super(expression, "t0");
    }

    @Override
    protected int result(int a) {
        return Integer.numberOfTrailingZeros(a);
    }
}
