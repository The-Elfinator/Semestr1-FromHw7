package expression;

public class Max extends Operation {

    public Max(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2, "max");
    }

    @Override
    public int result(int a, int b) {
        return Math.max(a, b);
    }
}
