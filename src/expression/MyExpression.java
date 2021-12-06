package expression;

public interface MyExpression extends ToMiniString, Expression {

    int evaluate(int x);

    String toString();

    boolean equals(Object object);

    String getTag();

    MyExpression getExpression1();

    MyExpression getExpression2();

    String getX();

    int getA();

    int hashCode();

}
