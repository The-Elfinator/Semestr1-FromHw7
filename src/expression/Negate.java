package expression;

public class Negate extends UnaryOperation {

    public Negate(MyExpression expression) {
        super(expression, "neg");
    }

    @Override
    protected int result(int a) {
        return -1 * a;
    }
}
