package expression.parser;

import expression.*;

import java.util.ArrayList;

public class ExpressionParser implements Parser {
    public ExpressionParser() {

    }

    @Override
    public MyTripleExpression parse(String expression) {
        return parse(new StringCharSource(expression));
    }


    public MyTripleExpression parse(CharSource source) {
        return new MyParser(source).parse();
    }

    private static class MyParser extends BaseParser {

        int countOfMinuses;

        MyParser(CharSource source) {
            super(source);
        }

        public MyTripleExpression parse() {
            boolean log = false;
            MyTripleExpression result;
            ArrayList<MyTripleExpression> stackOfExps = new ArrayList<>();
            ArrayList<String> queueOfOpers = new ArrayList<>();
            do {
                if (getCurrent() == ')') {
                    break;
                }
                if (isBegOfOperation(getCurrent()) && !stackOfExps.isEmpty()) {
                    String operation = parseOperation();
                    queueOfOpers.add(operation);
                }
                MyTripleExpression exp = parseElement();
                stackOfExps.add(exp);
            } while (!end());
            result = null;
            if (!stackOfExps.isEmpty()) {
                if (queueOfOpers.isEmpty()) {
                    result = stackOfExps.remove(stackOfExps.size() - 1);
                } else {
                    while (!queueOfOpers.isEmpty()) {
                        MyTripleExpression expression2 = stackOfExps.remove(stackOfExps.size() - 1);
                        MyTripleExpression expression1 = stackOfExps.remove(stackOfExps.size() - 1);
                        result = parseBinaryOperation(queueOfOpers.remove(queueOfOpers.size() - 1), expression1, expression2);
                    }
                }
            }
            return result;
        }

        private MyTripleExpression parseElement() {
            skipSpaces();
            final MyTripleExpression res = parseValue();
            skipSpaces();
            return res;
        }

        private MyTripleExpression parseValue() {
            if (getCurrent() == '(') {
                return parseExp();
            } else if (getCurrent() == '-') {
                take();
                countOfMinuses++;
                skipSpaces();
                int sign = 1; //1 - neg, 0 - pos
                while (take('-')) {
                    countOfMinuses++;
                    sign = sign == 1 ? 0 : 1;
                    skipSpaces();
                }
                if (between('0', '9')) {
                    countOfMinuses = 0;
                    return parseConstant(1);
                } else {
                    return parseUnaryOperation("-", parseElement());
                }
            } else if (between('0', '9')) {
                return parseConstant(0);
            } else if (between('x', 'z')) {
                return parseVariable();
            } else {
                System.err.println("I can't do it right now but the great force of \u262dSoviet Union\u262d will help me with that problem");
                throw new AssertionError("Error. Expected digit or 'x', 'y', 'z' or '-' or '(', found: " + getCurrent() + " " + (int) getCurrent());
            }
        }

        private MyTripleExpression parseExp() {
            skipSpaces();
            if (getCurrent() == ')') {
                take();
                return null;
            }
            return parse();
        }

        private MyTripleExpression parseBinaryOperation(String operation, MyTripleExpression exp1, MyTripleExpression exp2) {
            switch (operation) {
                case "+" -> {
                    return new Add(exp1, exp2);
                }
                case "-" -> {
                    return new Subtract(exp1, exp2);
                }
                case "*" -> {
                    return new Multiply(exp1, exp2);
                }
                case "/" -> {
                    return new Divide(exp1, exp2);
                }
            }
            return null;
        }

        private MyTripleExpression parseUnaryOperation(String operation, MyTripleExpression exp2) {
            switch (operation) {
                case "-" -> {
                    while (countOfMinuses > 0) {
                        countOfMinuses--;
                        return new Negate(parseUnaryOperation("-", exp2));
                    }
                    return exp2;
                }
            }
            return null;
        }

        private String parseOperation() {
            StringBuilder res = new StringBuilder();
            if (isOperation(getCurrent())) {
                res.append(take());
            }
            return res.toString();
        }

        private boolean isOperation(char current) {
            char[] ops = {'+', '*', '/', '-'};
            for (char c : ops) {
                if (current == c) {
                    return true;
                }
            }
            return false;
        }

        private boolean isBegOfOperation(char symb) {
            skipSpaces();
            char[] opers = {'+', '*', '/', '-'};
            for (char c : opers) {
                if (symb == c) {
                    return true;
                }
            }
            return false;
        }

        private MyTripleExpression parseVariable() {
            char var = take();
            return new Variable(Character.toString(var));
        }

        private MyTripleExpression parseConstant(int sign) {
            StringBuilder con = new StringBuilder();
            if (sign == 1) {
                con.append('-');
            }
            if (take('0')) {
                return new Const(0);
            }
            do {
                con.append(take());
            } while (between('0', '9'));
            return new Const(Integer.parseInt(con.toString()));
        }

        private void skipSpaces() {
            while (Character.isWhitespace(getCurrent())) {
                take();
            }
        }
    }
}