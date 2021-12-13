package expression.parser;

import expression.*;
import mypackage.BaseParser;
import mypackage.CharSource;
import mypackage.StringCharSource;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser implements Parser {

    @Override
    public TripleExpression parse(String expression) {
        return parse(new StringCharSource(expression));
    }

    private static TripleExpression parse(StringCharSource source) {
        return new MyParser(source).parse();
    }

    private static class MyParser extends BaseParser {

        public MyParser(CharSource source) {
            super(source);
        }

        public MyTripleExpression parse() {
            final MyTripleExpression result = parseElement();
            if (end()) {
                return result;
            }
            return null;
            //throw error("Expected end of input. Found non end");
        }

        private MyTripleExpression parseElement() {
            skipSpaces();
            MyTripleExpression res = parseExp();
            skipSpaces();
            return res;
        }

        private MyTripleExpression parseExp() {
            ArrayList<MyTripleExpression> stackOfExps = new ArrayList<>();
            ArrayList<Character> stackOfOper = new ArrayList<>();
            char ch = take();
            if ('x' <= ch && ch <= 'z') {
                stackOfExps.add(new Variable(Character.toString(ch)));
            } else if ('0' <= ch && ch <= '9') {
                stackOfExps.add(parseNumber(ch));
            } else if (isOperation(ch)) {
                stackOfOper.add(ch);
                parseElement();
            } else {
                return null;
            }
            while (!stackOfOper.isEmpty()) {
                char op = stackOfOper.remove(-1);
                MyTripleExpression exp1 = stackOfExps.remove(-1);
                MyTripleExpression exp2 = stackOfExps.remove(-1);
                stackOfExps.add(createExpression(op, exp1, exp2));
            }
            return stackOfExps.get(0);
        }

        private MyTripleExpression createExpression(char op, MyTripleExpression exp1, MyTripleExpression exp2) {
            switch (op) {
                case '+' -> {
                    return new Add(exp1, exp2);
                }
                case '-' -> {
                    return new Subtract(exp1, exp2);
                }
                case '*' -> {
                    return new Multiply(exp1, exp2);
                }
                case '/' -> {
                    return new Divide(exp1, exp2);
                }
                default -> throw new AssertionError("Found unknown operation");
            }
        }

        private MyTripleExpression parseNumber(char first) {
            final StringBuilder number = new StringBuilder();
            number.append(first);
            if (first == '0') {
                return new Const(0);
            }
            while (between('0', '9')) {
                number.append(take());
            }
            try {
                int r = Integer.parseInt(number.toString());
                return new Const(r);
            } catch (NumberFormatException e) {
                throw error("Invalid number: " + number + ": " + e.getMessage());
            }
        }

        private boolean isOperation(char c) {
            List<Character> oper = List.of('+', '-', '*', '/');
            for (Character s : oper) {
                if (c == s) {
                    return true;
                }
            }
            return false;
        }

        private void skipSpaces() {
            while (take(' ') || take('\t') || take('\r') || take('\n')){
                //do nothing

            }
        }


    }
}
