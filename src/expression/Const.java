package expression;

public class Const extends Monom implements MyExpression, MyTripleExpression {
    public Const (int a) {
        super(a);
        super.tag = "C";
    }

    @Override
    public int hashCode() {
        return super.a;
    }

    @Override
    public int evaluate(int x) {
        return this.a;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.a;
    }
    @Override
    public String toString() {
        return Integer.toString(a);
    }

}
