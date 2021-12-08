package expression;

public abstract class UnaryOperation implements TripleExpression {
    MyTripleExpression expression;
    String tag;

    protected UnaryOperation(MyTripleExpression expression, String tag) {
        this.expression = expression;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public MyTripleExpression getExpression() {
        return expression;
    }

    protected abstract int result(int a);

    public int evaluate(int x, int y, int z) {
        int a = expression.evaluate(x, y, z);
        return result(a);
    }

}
