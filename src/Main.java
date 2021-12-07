import expression.*;

public class Main {

    public static void main(String[] args) {
        Add add = new Add(new Variable("x"), new Const(2));

        //System.out.println(new Const(2).evaluate(0, 0, 0));
        //System.out.println(add.evaluate(0, 0, 0));

    }
}
