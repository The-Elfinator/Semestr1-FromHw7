import expression.*;
import expression.parser.ExpressionParser;


public class Main {

    public static void main(String[] args) {
        Negate negate = new Negate(new Add(new Variable("x"), new Const(2)));
        // System.out.println(negate.evaluate(0, 0, 0));
        System.out.println('\u262d');
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("1\u000B"));
        System.out.println(parser.parse("y"));
        System.out.println(parser.parse("1 + 2"));
        System.out.println(parser.parse("1 * 2"));
        System.out.println(parser.parse("1 / 2"));
        System.out.println(parser.parse("1 - 2"));
        System.out.println(parser.parse("1 +    2 \t "));
        System.out.println(parser.parse("3 * x"));
        System.out.println(parser.parse("3 - z"));
        System.out.println(parser.parse("-3 - z"));
        System.out.println(parser.parse("3 - -z"));
        System.out.println(parser.parse("-3 - -z"));
        System.out.println(parser.parse("----------------3"));
        System.out.println(parser.parse("---------------3"));
        System.out.println(parser.parse("3 + x * z"));
        System.out.println(parser.parse("3 * x + \n         z"));
        System.out.println(parser.parse("3 * (x + z)     "));
        System.out.println(parser.parse("(3 + x) * z"));
        System.out.println(parser.parse("0 + -1"));
        MyTripleExpression expression = parser.parse("(0 + -1)");
        System.out.println(expression);
        System.out.println(parser.parse(""));
        // System.out.println(new Const(2).evaluate(0, 0, 0));
        // System.out.println(add.evaluate(0, 0, 0));

    }

}
