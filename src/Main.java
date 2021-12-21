import expression.*;
import expression.parser.ExpressionParser;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        try {
            Negate negate = new Negate(new Negate(new Add(new Variable("x"), new Const(2))));
            // System.out.println(negate);
            // System.out.println(negate.evaluate(0, 0, 0));
            // System.out.println('\u262d');
//
//        System.out.println(exp);
            //((((y + x) + -2147483648) - x) + x)
            //f(-1534516706, -816864994, 1077556082)
            System.out.println(new ExpressionParser().parse("1 + 1 - - 1"));
            int x = -1534516706;
            int y = -816864994;
            int z = 1077556082;
            int r = ((((y + x) + (-2147483648)) - x) + x);

            System.out.println(new ExpressionParser().parse("-(-2147483648)"));
            System.out.println(new ExpressionParser().parse("--2147483648"));

//            System.out.println(r);
//            System.out.println(new ExpressionParser().parse("((((y + x) + -2147483648) - x) + x)").evaluate(-1534516706, -816864994, 1077556082));
            boolean runTest = false;
            String s = "\n" +
                    "\n" +
                    "((     (y\n" +
                    "+ x)\n" +
                    "\n" +
                    "\n" +
                    "--2147483648)+\n" +
                    "         (x+ x)   )";
            //System.out.println(y + x - -2147483648 + x  + x);
           // System.out.println(new ExpressionParser().parse("y + x - -2147483648 + x + x"));
            //System.out.println(new ExpressionParser().parse(s).evaluate(-1534516706, -816864994, 1077556082));
            //System.out.println(new ExpressionParser().parse("((x + y))"));
            if (runTest) {
                ExpressionParser parser = new ExpressionParser();
                System.out.println(parser.parse("1\u000B"));
                System.out.println(parser.parse("y\n"));
                System.out.println(parser.parse("---1"));
                System.out.println(parser.parse("-   -\r - \n  1"));
                System.out.println(parser.parse("-\r-  \n  -   \tx"));
                System.out.println(parser.parse("1 + 2"));
                System.out.println(parser.parse("   1 \n\r * \r\t 2   "));
                System.out.println(parser.parse("1\n / -2"));
                System.out.println(parser.parse("   1 -   -2"));
                System.out.println(parser.parse("  --- 1 +    2 \t "));
                System.out.println(parser.parse("3 * - x"));
                System.out.println(parser.parse("3 - \nz\r"));
                System.out.println(parser.parse("-\t3 - z"));
                System.out.println(parser.parse("3 - -z"));
                System.out.println(parser.parse("-3 - -z"));
                System.out.println(parser.parse("----------------3"));
                System.out.println(parser.parse("---------------3"));
                System.out.println(parser.parse("1 - ------x"));
                System.out.println(parser.parse("1 -------x"));
                System.out.println(parser.parse("  0   -   -  1  "));
                System.out.println(parser.parse("0   --  1"));
                System.out.println(parser.parse("0+-1"));
                System.out.println(parser.parse("0--x"));
                System.out.println(parser.parse("-(-1)"));
                System.out.println(parser.parse(" \r -\t (\n-   1)"));
                System.out.println(">>>>>>>>>>>>>>>>>>>>");
                //--------------------------------------------
                // testing '(' and ')'
                //--------------------------------------------
                System.out.println(parser.parse("0 + -1"));
                //System.out.println(parser.parse("(\r\t\n)"));
                System.out.println(parser.parse("(0 + -1)"));
                System.out.println(parser.parse("(3 + x) * z"));
                System.out.println(parser.parse("3 * \r(\nx + z\r)\t     "));
                //System.out.println(parser.parse(""));
                System.out.println(parser.parse("((x + 2)) * ((((x * z)) * 2))"));
                System.out.println(parser.parse("x * (x * (x + 1))"));
                System.out.println(parser.parse("  \n( \t  y *\t(y * (\r\n\t2147483647 + x\r\n\t)\t\n)\r)\n"));
                System.out.println(parser.parse("((1)+1)"));
                System.out.println(parser.parse("(\t(\r1\n)\t+1\t)\r"));
                System.out.println(parser.parse("((1))"));
                System.out.println(parser.parse("((x))"));
                System.out.println(parser.parse("((x + y))"));
                System.out.println(parser.parse("(\r1 + (x*y)\t)"));
                System.out.println(parser.parse("(\r(x*y)\t)"));
                System.out.println(parser.parse("((x + y) + x)"));
                System.out.println(">>>>>>>>>>>>>>>>>>>>");
                //--------------------------------------------
                // testing priorities
                //--------------------------------------------
                System.out.println(parser.parse("x + y + x"));
                System.out.println(parser.parse("x*y*x"));
                System.out.println(parser.parse("3 + x * z"));
                System.out.println(parser.parse("3 * x + z"));
                // System.out.println(new Const(2).evaluate(0, 0, 0));
                // System.out.println(add.evaluate(0, 0, 0));
            }
            boolean runTestMod = false;
            if (runTestMod) {
                ExpressionParser parseMod = new ExpressionParser();
                System.out.println(parseMod.parse("2 min 3"));
                System.out.println(parseMod.parse("2 max 3"));
                System.out.println(parseMod.parse("1 + 2 * 3 min - 2 + x * y / -z max - - -34"));
                System.out.println(parseMod.parse("1 + x / - -y min (34 min x * z - -10)"));
            }

        } catch (StackOverflowError e) {
            System.out.println("Stack over flow " + e.getMessage());
        }
        System.out.println(Integer.numberOfTrailingZeros(0));
    }

}
