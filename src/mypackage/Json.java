package mypackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Json {
    public static Object parse(final String string) {
        return parse(new StringCharSource(string));
    }

    public static Object parse(final CharSource source) {
        return new JsonParser(source).parse();
    }

    private static class JsonParser extends BaseParser {

        public JsonParser(CharSource source) {
            super(source);
        }

        public Object parse() {
            final Object result = parseElement();
            if (!end()) {
                throw error("Expected end of input");
            }
            return result;
        }

        private Object parseElement() {
            skipWhitespace();
            final Object result = parseValue();
            skipWhitespace();
            return result;
        }

        private void skipWhitespace() {
            while (take(' ') || take('\n') || take('\r') || take('\t')) {
                //Do nothing
            }
        }

        private Object parseValue() {
            if (take('{')) {
                return parseObject();
            } else if (take('[')) {
                return parseArray();
            } else if (take('"')) {
                return parseString();
            }
            if (take('n')) {
                expect("ull");
                return null;
            } else if (take('t')) {
                expect("rue");
                return true;
            } else if (take('f')) {
                expect("alse");
                return false;
            } else {
                return parseNumber();
            }

        }

        private Object parseObject() {
            skipWhitespace();
            if (take('}')) {
                return Map.of();
            }
            final Map<String, Object> object = new HashMap<>();
            do {
                parseMember(object);
            } while (take(','));
            expect('}');
            return object;
        }

        private void parseMember(final Map<String, Object> object) {
            skipWhitespace();
            expect('"');
            final String key = parseString();
            skipWhitespace();
            expect(':');
            final Object value = parseElement();
            object.put(key, value);
        }

        private List<Object> parseArray() {
            skipWhitespace();
            if (take(']')) {
                return List.of();
            }
            final List<Object> array = new ArrayList<>();
            array.add(parseValue());
            skipWhitespace();
            while (take(',')) {
                skipWhitespace();
                array.add(parseValue());
                skipWhitespace();
            }
            expect(']');
            return array;
        }

        private Integer parseNumber() {
            final StringBuilder number = new StringBuilder();
            if (take('-')) {
                number.append('-');
            }
            if (take('0')) {
                return 0;
            }
            while (between('0', '9')) {
                number.append(take());
            }
            try {
                return Integer.parseInt(number.toString());
            } catch (NumberFormatException e) {
                throw error("Invalid number: " + number + ": " + e.getMessage());
            }
        }

        private String parseString() {
            final StringBuilder string = new StringBuilder();
            while (!take('"')) {
                string.append(take());
            }
            return string.toString();
        }
    }
}
