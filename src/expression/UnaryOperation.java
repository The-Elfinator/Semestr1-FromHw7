package expression;

public abstract class UnaryOperation implements MyTripleExpression {
    MyExpression expression;
    String tag;

    protected UnaryOperation(MyExpression expression, String tag) {
        this.expression = expression;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public MyExpression getExpression() {
        return expression;
    }

    @Override
    public String toString(){
        String a = this.expression.toString();
        if (this.expression instanceof Const || this.expression instanceof Variable || this.expression instanceof UnaryOperation) {
            return "-(" + a + ")";
        }
        return "-" + a;
    }

    protected abstract int result(int a);

    public int evaluate(int x) {
        int a = expression.evaluate(x);
        return result(a);
    }

    public int evaluate(int x, int y, int z) {
        int a = ((MyTripleExpression) expression).evaluate(x, y, z);
        return result(a);
    }

}
