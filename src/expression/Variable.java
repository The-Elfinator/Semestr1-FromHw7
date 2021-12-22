package expression;


public class Variable extends Monom implements MyExpression, MyTripleExpression {

    public Variable(String x) {
        super(x);
        super.tag = "V";
    }

    @Override
    public int hashCode() {
        return super.x.charAt(0) * 7373;
    }

    @Override
    public int evaluate(int x) {
        System.err.println("Evaluating variable");
        return x;
    }

    @Override
    public String toString() {
        return this.x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (this.x.toLowerCase()) {
            case "x" -> {
                return x;
            }
            case "y" -> {
                return y;
            }
            case "z" -> {
                return z;
            }
            default -> {
                return 0;
            }
        }
    }
}
