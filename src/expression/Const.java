package expression;

public class Const extends Monom {
    public Const (int a) {
        super(a);
        super.tag = "C";
    }


    @Override
    public int evaluate(int x) {
        return this.a;
    }

    @Override
    public String toString() {
        return Integer.toString(a);
    }

    @Override
    public boolean equals(Expression expression) {
        return super.equals(expression, "C");
    }

}
