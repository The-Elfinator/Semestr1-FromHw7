package expression;

public abstract class Monom implements Expression {
    int a;
    String x;
    String tag;

    public Monom(int a) {
        this.a = a;
    }

    public Monom(String x) {
        this.x = x;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    public String getX() {
        return this.x;
    }

    public int getA() {
        return this.a;
    }

    @Override
    public Expression getExpression1() {
        return null;
    }

    @Override
    public Expression getExpression2() {
        return null;
    }

    public boolean equals(Expression expression, String tag) {
        boolean flag = tag.equals(expression.getTag());
        if (!flag) {
            return false;
        }
        if (tag.equals("V")) {
            flag = this.x.equals(expression.getX());
        } else {
            flag = this.a == expression.getA();
        }
        return flag;
    }

}
