package expression.exceptions;

public class DivideByZeroException extends RuntimeException {
    public DivideByZeroException() {
        super("Divide by 0");
    }
}
