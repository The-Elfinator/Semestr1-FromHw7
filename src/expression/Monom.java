package expression;

public abstract class Monom implements MyTripleExpression {
    int a;
    String x;
    String tag;

    public Monom(int a) {
        this.a = a;
        this.tag = "C";
    }

    public Monom(String x) {
        this.x = x;
        this.tag = "V";
        this.a = Integer.MAX_VALUE;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    public String getX() {
        return this.x;
    }

    public int getA() {
        return this.a;
    }

    @Override
    public abstract int hashCode();

    public abstract int evaluate(int x);
    public abstract int evaluate(int x, int y, int z);

    public boolean equals(Object object) {
        if  (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        if (this.tag.equals(((Monom) object).getTag())
                && this.a == ((Monom) object).getA()) {
            if (this.x == null) {
                return null == ((Monom) object).getX();
            }
            return this.x.equals(((Monom) object).getX());
        }
        return false;
    }

}
