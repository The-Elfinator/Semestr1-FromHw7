package expression;

public abstract class Operation implements Expression {
    Expression expression1, expression2;
    String tag;

    protected Operation(Expression expression1, Expression expression2, String tag) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }

    public int getA() {
        return -1;
    }

    public String getX() {
        return null;
    }

    @Override
    public int evaluate(int x) {
        int a = expression1.evaluate(x);
        int b = expression2.evaluate(x);
        switch (tag) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                return a / b;
            }
            default -> throw new AssertionError("UKNOWN tag");
        }
    }

    @Override
    public String toString() {
        String a = expression1.toString();
        String b = expression2.toString();
        StringBuilder sb = new StringBuilder("(");
        sb.append(a).append(" ");
        switch (tag) {
            case "+" -> {
                sb.append("+");
            }
            case "-" -> {
                sb.append("-");
            }
            case "*" -> {
                sb.append("*");
            }
            case "/" -> {
                sb.append("/");
            }
            default -> throw new AssertionError("UKNOWN tag");
        }
        sb.append(" ");
        sb.append(b).append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Expression expression) {
        return this.tag.equals(expression.getTag())
                && this.expression1.equals(expression.getExpression1())
                && this.expression2.equals(expression.getExpression2());
    }
}
