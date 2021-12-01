package expression;

public class Variable extends Monom {

    public Variable(String x) {
        super(x);
        super.tag = "V";
    }


    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return this.x;
    }

    @Override
    public boolean equals(Expression expression) {
        return super.equals(expression, "V");
    }

}
