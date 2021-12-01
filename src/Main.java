import expression.*;

public class Main {

    public static void main(String[] args) {
        //int x = Integer.parseInt(args[0]);
        Expression exp1 = new Add(new Multiply(new Const(2), new Const(3)), new Const(1));
        Expression exp2 = new Add(new Multiply(new Const(2), new Const(3)), new Add(new Const(1), new Const(1)));
        System.out.println(exp1.equals(exp2));
        //System.out.println(expression.evaluate(5));

    }
}
