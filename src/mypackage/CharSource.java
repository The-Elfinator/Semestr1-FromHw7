package mypackage;

public interface CharSource {
    char next();
    boolean hasNext();

    IllegalArgumentException error(String message);
}
