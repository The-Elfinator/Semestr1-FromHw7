package expression;

public class Min extends Operation {

    public Min(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2, "min");
    }

    @Override
    public int result(int a, int b) {
        return Math.min(a, b);
    }
}
