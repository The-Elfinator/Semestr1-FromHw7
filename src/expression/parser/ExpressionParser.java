package expression.parser;

import expression.*;
import mypackage.BaseParser;
import mypackage.CharSource;
import mypackage.StringCharSource;

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

        MyParser(CharSource source) {
            super(source);
        }

        public MyTripleExpression parse() {
            skipSpaces();
            MyTripleExpression result;
            ArrayList<MyTripleExpression> stackOfExps = new ArrayList<>();
            ArrayList<String> queueOfOpers = new ArrayList<>();
            do {
                if (getCurrent() == ')') {
                    take();
                    break;
                }
                if (!stackOfExps.isEmpty() && isBegOfOperation(getCurrent())) {
                    String operation = parseOperation();
                    if (operation.equals("*") || operation.equals("/")) {
                        stackOfExps.add(parseBinaryOperation(
                                operation, stackOfExps.remove(stackOfExps.size() - 1), parseElement()
                        ));
                    } else {
                        queueOfOpers.add(operation);
                        stackOfExps.add(parse());
                    }
                } else {
                    stackOfExps.add(parseElement());
                }
            } while (!end());
            MyTripleExpression exp2 = stackOfExps.remove(stackOfExps.size() - 1);
            if (!queueOfOpers.isEmpty()) {
                if (stackOfExps.isEmpty()) {
                    result = parseUnaryOperation(queueOfOpers.remove(queueOfOpers.size() - 1), exp2);
                } else {
                    MyTripleExpression exp1 = stackOfExps.remove(stackOfExps.size() - 1);
                    result = parseBinaryOperation(queueOfOpers.remove(queueOfOpers.size() - 1), exp1, exp2);
                }
            } else {
                result = exp2;
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
            if (take('(')) {
                return parseExp();
            } else if (take('-')) {
                return parseUnaryOperation("-", parseElement());
            } else if (between('0', '9')) {
                return parseConstant();
            } else if (between('x', 'z')) {
                return parseVariable();
            } else {
                System.err.println("I can't do it right now but the great force of \u262dSoviet Union\u262d will help me with that problem");
                throw new AssertionError("Error. Expected digit or x..z or '-' or '(', found: " + (int) getCurrent());
            }
        }

        private MyTripleExpression parseExp() {
            skipSpaces();
            if (take(')')) {
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
                    return new Negate(exp2);
                }
            }
            return null;
        }

        private String parseOperation() {
            StringBuilder res = new StringBuilder();
            while (isOper(getCurrent())) {
                res.append(take());
            }
            return res.toString();
        }

        private boolean isOper(char current) {
            char[] ops = {'+', '*', '/', '-'};
            for (char c : ops) {
                if (current == c) {
                    return true;
                }
            }
            return false;
        }

        private boolean isBegOfOperation(char symb) {
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

        private MyTripleExpression parseConstant() {
            StringBuilder con = new StringBuilder();
            if (take('0')) {
                return new Const(0);
            }
            do {
                con.append(take());
            } while (between('0', '9'));
            return new Const(Integer.parseInt(con.toString()));
        }

        private void skipSpaces() {
            while (take(' ') || take('\t') || take('\r') || take('\n') || take('\u000B'));
        }
    }
}