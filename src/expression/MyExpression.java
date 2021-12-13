package expression;

public interface MyExpression extends ToMiniString, Expression {

    int evaluate(int x);

    String toString();

    boolean equals(Object object);

    String getTag();

    int hashCode();

}
