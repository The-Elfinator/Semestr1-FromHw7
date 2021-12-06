package expression;

public interface MyTripleExpression extends ToMiniString, Expression {

    int evaluate(int x, int y, int z);

    String toString();

    boolean equals(Object object);

    String getTag();

    MyTripleExpression getExpression1();

    MyTripleExpression getExpression2();

    String getX();

    int getA();

    int hashCode();

}
