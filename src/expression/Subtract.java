package expression;

public class Subtract extends Operation {

    public Subtract(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2, "-");
    }

    @Override
    public int result(int x, int y) {
        return x - y;
    }
}
