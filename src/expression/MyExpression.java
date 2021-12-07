package expression;

public interface MyExpression extends ToMiniString, Expression {

    int evaluate(int x);
    int evaluate(int x, int y, int z);

    String toString();

    boolean equals(Object object);

    String getTag();

    int hashCode();

}
