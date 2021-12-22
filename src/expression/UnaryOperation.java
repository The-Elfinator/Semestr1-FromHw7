package expression;

public abstract class UnaryOperation implements MyTripleExpression {
    MyExpression expression;
    String tag;

    protected UnaryOperation(MyExpression expression, String tag) {
        this.expression = expression;
        this.tag = tag;
//        System.err.println(expression.getClass() + " " + tag);
//        System.err.println("++++++++++++++++++++++++++++");
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
        String tag1 = this.tag.equals("neg") ? "-" : this.tag;
        return tag1 + "(" + a + ")";
    }

    protected abstract int result(int a);

    public int evaluate(int x) {
        int a = expression.evaluate(x);
        return result(a);
    }

    public int evaluate(int x, int y, int z) {
        //System.err.println("-(" + expression + ")");
        int a = ((MyTripleExpression) expression).evaluate(x, y, z);
        return result(a);
    }

}
