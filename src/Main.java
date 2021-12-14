import expression.*;
import expression.parser.ExpressionParser;


public class Main {

    public static void main(String[] args) {
        Negate negate = new Negate(new Add(new Variable("x"), new Const(2)));
        // System.out.println(negate.evaluate(0, 0, 0));
        System.out.println('\u262d');
        System.out.println(new ExpressionParser().parse("1\u000B"));
        System.out.println(new ExpressionParser().parse("y"));
        System.out.println(new ExpressionParser().parse("1 + 2"));
        System.out.println(new ExpressionParser().parse("1 * 2"));
        System.out.println(new ExpressionParser().parse("1 / 2"));
        System.out.println(new ExpressionParser().parse("1 - 2"));
        System.out.println(new ExpressionParser().parse("1 +    2 \t "));
        System.out.println(new ExpressionParser().parse("3 * x"));
        System.out.println(new ExpressionParser().parse("3 - z"));
        System.out.println(new ExpressionParser().parse("-3 - z"));
        System.out.println(new ExpressionParser().parse("3 - -z"));
        System.out.println(new ExpressionParser().parse("-3 - -z"));
        System.out.println(new ExpressionParser().parse("3 + x * z"));
        System.out.println(new ExpressionParser().parse("3 * x + z"));
        System.out.println(new ExpressionParser().parse("3 * (x + z)"));
        System.out.println(new ExpressionParser().parse("(3 + x) * z"));
        // System.out.println(new Const(2).evaluate(0, 0, 0));
        // System.out.println(add.evaluate(0, 0, 0));

    }

}
