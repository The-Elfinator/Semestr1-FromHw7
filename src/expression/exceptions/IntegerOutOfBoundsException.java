package expression.exceptions;

public class IntegerOutOfBoundsException extends RuntimeException {
    public IntegerOutOfBoundsException() {
        super("Overflow");
    }
}
