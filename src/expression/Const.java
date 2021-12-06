package expression;

public class Const extends Monom implements MyExpression {
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
    public String toString() {
        return Integer.toString(a);
    }

}
