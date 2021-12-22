package expression.exceptions;

import expression.*;
import expression.parser.*;

import java.util.ArrayList;

public class ExpressionParser implements Parser {
    protected boolean log = false;

    public ExpressionParser() {

    }

    @Override
    public MyTripleExpression parse(String expression) {
        if (log) {
            System.err.println(">>>>>");
            System.err.println(expression);
            System.err.println(">>>>>");
        }
        return parse(new StringCharSource(expression));
    }


    public MyTripleExpression parse(CharSource source) {
        return new MyParser(source).parse();
    }

    private static class MyParser extends BaseParser {

        private int countOfBrackets = 0;
        private char prev = 0;

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
                    countOfBrackets--;
                    if (countOfBrackets < 0) {
                        throw new ExtraBracketsException("Found extra ')'");
                    }
                    take();
                    break;
                }
                if (isBegOfOperation(getCurrent()) && !stackOfExps.isEmpty()) {
                    String operation = parseOperation();
                    skipSpaces();
                    queueOfOpers.add(operation);
                }
                MyTripleExpression exp = parseElement();
                stackOfExps.add(exp);
            } while (!end());
            if (end() && countOfBrackets != 0) {
                throw new ExtraBracketsException("Found extra '('");
            }
//
//            for (MyTripleExpression e : stackOfExps) {
//                System.out.println(e.getClass() + " " + e);
//            }
//            for (String s : queueOfOpers) {
//                System.out.println(s);
//            }
//
            if (stackOfExps.size() != queueOfOpers.size() + 1) {
                throw new UndefinedExpression("Found unknown expression");
            }
            result = null;
            if (!stackOfExps.isEmpty()) {
                if (queueOfOpers.isEmpty()) {
                    result = stackOfExps.remove(stackOfExps.size() - 1);
                } else {
                    int i = 0;
                    while (i < queueOfOpers.size()) {
                        if (queueOfOpers.get(i).equals("*") || queueOfOpers.get(i).equals("/")) {
                            MyTripleExpression expression1 = stackOfExps.get(i);
                            MyTripleExpression expression2 = stackOfExps.get(i + 1);
                            MyTripleExpression res = parseBinaryOperation(queueOfOpers.get(i), expression1, expression2);
                            stackOfExps.set(i, res);
                            stackOfExps.remove(i+1);
                            queueOfOpers.remove(i);
                        } else {
                            i++;
                        }
                    }
                    i = 0;
                    while (i < queueOfOpers.size()) {
                        if (queueOfOpers.get(i).equals("+") || queueOfOpers.get(i).equals("-")) {
                            MyTripleExpression expression1 = stackOfExps.get(i);
                            MyTripleExpression expression2 = stackOfExps.get(i + 1);
                            MyTripleExpression res = parseBinaryOperation(queueOfOpers.get(i), expression1, expression2);
                            stackOfExps.set(i, res);
                            stackOfExps.remove(i+1);
                            queueOfOpers.remove(i);
                        } else {
                            i++;
                        }
                    }
                    i = 0;
                    while (i < queueOfOpers.size()) {
                        if (queueOfOpers.get(i).equals("min") || queueOfOpers.get(i).equals("max")) {
                            MyTripleExpression expression1 = stackOfExps.get(i);
                            MyTripleExpression expression2 = stackOfExps.get(i + 1);
                            MyTripleExpression res = parseBinaryOperation(queueOfOpers.get(i), expression1, expression2);
                            stackOfExps.set(i, res);
                            stackOfExps.remove(i+1);
                            queueOfOpers.remove(i);
                        } else {
                            i++;
                        }
                    }
                    result = stackOfExps.get(0);
                }
            }
//            System.err.println(">>>>>>>>>>>>>>");
//            System.err.println(result.getClass());
//            System.err.println(result);
//            System.err.println(">>>>>>>>>>>>>>");
            return result;
        }

        private MyTripleExpression parseElement() {
            skipSpaces();
            final MyTripleExpression res = parseValue();
            skipSpaces();
            return res;
        }

        private MyTripleExpression parseValue() {
            //System.err.println(getCurrent());
            if (getCurrent() == '(') {
                countOfBrackets++;
                return parseExp();
            } else if (getCurrent() == '-') {
                int countOfMinuses = 1;
                take();
                //System.err.println(countOfMinuses);
                skipSpaces();
                int sign = 1; //1 - neg, 0 - pos
                while (take('-')) {
                    //System.err.println("I'm here");
                    countOfMinuses++;
                    sign = sign == 1 ? 0 : 1;
                    skipSpaces();
                }
                if (between('0', '9')) {
                    return parseConstant(countOfMinuses);
                } else {
                    MyTripleExpression expression = parseElement();
                    return parseUnaryOperation("-", expression, countOfMinuses);
                }
            } else if (getCurrent() == 'l' || getCurrent() == 't') {
                String operation = parseOperation();
                MyTripleExpression expression = parseElement();
                return parseUnaryOperation(operation, expression, 0);
            } else if (between('0', '9')) {
                return parseConstant(0);
            } else if (between('x', 'z')) {
                return parseVariable();
            } else {
                throw new IllegalParsingLine(String.format(
                        "Unknown elements. Expected digit or 'x', 'y', 'z' or operation or '('. Found: %s with id: %n",
                        getCurrent(), (int) getCurrent()
                ));
            }
        }

        private MyTripleExpression parseExp() {
            skipSpaces();
            take();
            if (getCurrent() == ')') {
                take();
                return null;
            }
            skipSpaces();
            return parse();
        }

        private MyTripleExpression parseBinaryOperation(String operation, MyTripleExpression exp1, MyTripleExpression exp2) {
            switch (operation) {
                case "+" -> {
                    return new CheckedAdd(exp1, exp2);
                }
                case "-" -> {
                    return new CheckedSubtract(exp1, exp2);
                }
                case "*" -> {
                    return new CheckedMultiply(exp1, exp2);
                }
                case "/" -> {
                    return new CheckedDivide(exp1, exp2);
                }
                case "min" -> {
                    return new Min(exp1, exp2);
                }
                case "max" -> {
                    return new Max(exp1, exp2);
                }
            }
            return null;
        }

        private MyTripleExpression parseUnaryOperation(String operation, MyTripleExpression exp2, int countOfMinuses) {
            skipSpaces();
            switch (operation) {
                case "-" -> {
                    while (countOfMinuses > 0) {
                        // System.err.println("In loop negate " + countOfMinuses);
                        countOfMinuses--;
                        return new CheckedNegate(parseUnaryOperation("-", exp2, countOfMinuses));
                    }
                    return exp2;
                }
                case "l0" -> {
                    return new LeftZeroes(exp2);
                }
                case "t0" -> {
                    return new RightZeroes(exp2);
                }
            }
            return null;
        }

        private String parseOperation() {
            StringBuilder res = new StringBuilder();
            this.prev = getPrev();
            if (isOperation(getCurrent())) {
                res.append(take());
            } else if (getCurrent() == 'l') {
                res.append(take());
                if (test('0')) {
                    res.append(take());
                } else {
                    parseOperError();
                }
            } else if (getCurrent() == 't') {
                res.append(take());
                if (test('0')) {
                    res.append(take());
                } else {
                    parseOperError();
                }
            } else if (getCurrent() == 'm') {
                res.append(take());
                if (test('i')) {
                    res.append(take());
                    if (test('n')) {
                        res.append(take());
                    } else {
                        parseOperError();
                    }
                } else if (test('a')) {
                    res.append(take());
                    if (test('x')) {
                        res.append(take());
                    } else {
                        parseOperError();
                    }
                } else {
                    parseOperError();
                }
            }
            char ch = getCurrent();
            if (res.toString().equals("min") || res.toString().equals("max")) {
                if (!((this.prev == ')' || Character.isWhitespace(this.prev))
                        && (Character.isWhitespace(ch) || ch == '-' || ch == '('))) {
                    throw new IllegalMinMaxParseException(String.format("Wrong input expression. Found %s%s%s", this.prev, res, ch));
                }
            }
            else if (res.toString().equals("l0") || res.toString().equals("t0")) {
                if (!(Character.isWhitespace(ch) || ch == '-' || ch == '(')) {
                    throw new IllegalLeadTrialZeroesException(String.format("Wrong input expression. Found s%s", res, ch));
                }
            }
            skipSpaces();
            return res.toString();
        }

        private void parseOperError() {
            throw new AssertionError("Found unknown operation. Unknown symbol " + getCurrent());
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
            char[] opers = {'+', '*', '/', '-', 'm'};
            for (char c : opers) {
                if (symb == c) {
                    return true;
                }
            }
            return false;
        }

        private MyTripleExpression parseVariable() {
            skipSpaces();
            char var = take();
            skipSpaces();
            return new Variable(Character.toString(var));
        }

        private MyTripleExpression parseConstant(int cntOfMinus) {
            skipSpaces();
            if (cntOfMinus > 1) {
                return new CheckedNegate(parseConstant(cntOfMinus - 1));
            }
            StringBuilder con = new StringBuilder();
            if (cntOfMinus == 1) {
                con.append('-');
            }
            if (take('0')) {
                return new Const(0);
            }
            do {
                con.append(take());
            } while (between('0', '9'));
            skipSpaces();
            return new Const(Integer.parseInt(con.toString()));
        }

        private void skipSpaces() {
            while (Character.isWhitespace(getCurrent())) {
                this.prev = getCurrent();
                take();
            }
        }
    }
}