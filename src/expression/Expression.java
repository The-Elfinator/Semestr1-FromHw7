package expression;

public interface Expression {

    int evaluate(int x);

    String toString();

    boolean equals(Expression expression);

    String getTag();

    Expression getExpression1();

    Expression getExpression2();

    String getX();

    int getA();
}
