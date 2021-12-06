package expression;

public abstract class Operation implements MyExpression {
    MyExpression expression1, expression2;
    String tag;

    public Operation(MyExpression expression1, MyExpression expression2, String tag) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public MyExpression getExpression1() {
        return expression1;
    }

    public MyExpression getExpression2() {
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
            default -> throw new AssertionError("UNKNOWN tag");
        }
    }

    @Override
    public String toString() {
        String a = expression1.toString();
        String b = expression2.toString();
        return "(" + a + " " + tag + " " + b + ")";
    }

    @Override
    public int hashCode() {
        return expression1.hashCode() * 13
                + (((int) this.tag.charAt(0) + 5377) * 2327 + 1243) % ((int) 1e9 + 7)
                + expression2.hashCode() * 23;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        return this.tag.equals(((Operation) object).getTag())
                && this.expression1.equals(((Operation) object).getExpression1())
                && this.expression2.equals(((Operation) object).getExpression2());
    }
}
