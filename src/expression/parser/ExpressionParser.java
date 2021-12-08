package expression.parser;

import expression.TripleExpression;
import mypackage.BaseParser;
import mypackage.CharSource;
import mypackage.StringCharSource;

public class ExpressionParser implements Parser {

    public ExpressionParser() {

    }

    @Override
    public TripleExpression parse(String expression) {
        return parse(new StringCharSource(expression));
    }

    private TripleExpression parse(StringCharSource source) {
        return new MyParser(source).parse();
    }

    private static class MyParser extends BaseParser {

        public MyParser(CharSource source) {
            super(source);
        }

        public TripleExpression parse() {
            TripleExpression result = parseElement();
            if (end()) {
                return result;
            }
            throw error("Expected end of input. Found non end");
        }

        private TripleExpression parseElement() {
            skipSpaces();
            TripleExpression res = parseExp();
            skipSpaces();
            return null;
        }

        private TripleExpression parseExp() {
            if (take('-')) {

            }
            return null;
        }

        private void skipSpaces() {
            while (take(' ') || take('\t') || take('\r') || take('\n'));
        }


    }
}
