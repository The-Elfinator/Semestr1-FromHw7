package expression;

public abstract class Monom implements MyExpression {
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
    public MyExpression getExpression1() {
        return null;
    }

    @Override
    public abstract int hashCode();

    @Override
    public MyExpression getExpression2() {
        return null;
    }

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
