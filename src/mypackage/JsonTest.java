package mypackage;

import org.junit.Assert;
import org.junit.Test;

public class JsonTest {

    private void valid(final Object expected, final String json) {
        Assert.assertEquals("Input: \"" + json + "\"", expected, Json.parse(json));
    }



    private void invalid(final String invalidJson) {
        try {
            final Object result = Json.parse(invalidJson);
            Assert.fail(String.format("Expected error for \"%s\", parsed %s", invalidJson, result));
        } catch (final IllegalArgumentException e) {
            System.out.format("Expected error%n\tinput: %s%n\tmessage: %s%n", invalidJson, e.getMessage());
        }
    }

    @Test
    public void testNull() {
        valid(null, "null");
        invalid("NULL");
        invalid("nULL");
        invalid("nullnull");
    }

    @Test
    public void testWhitespace() {
        valid(null, "    null");
        valid(null, "null    ");
        valid(null, "   null   ");
        valid(null, " \r\n\t  null  \r\n\t   ");
    }
    @Test
    public void testBoolean() {
        valid(true, "true");
        valid(false, "false");
    }
}
