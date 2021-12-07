package expression;

public interface MyTripleExpression extends MyExpression, TripleExpression {

    int evaluate(int x, int y, int z);

}
