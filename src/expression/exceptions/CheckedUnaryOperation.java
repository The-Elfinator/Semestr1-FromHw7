package expression.exceptions;

import expression.MyExpression;
import expression.UnaryOperation;

public abstract class CheckedUnaryOperation extends UnaryOperation {
    protected CheckedUnaryOperation(MyExpression expression, String tag) {
        super(expression, tag);
    }
}
