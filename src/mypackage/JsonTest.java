package mypackage;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void testStrings() {
        valid("hello", "\"hello\"");
        valid("   hello   ", "\"   hello   \"");
        valid("hello", "   \"hello\"   ");
    }

    @Test
    public void testIntegers() {
        valid(10, "10");
        valid(10, "   10   ");
        valid(0, "0");
        valid(0, "-0");
        invalid("00");
        invalid("01");
        invalid("0x1");
        valid(1234567890, "1234567890");
        valid(-1234567890, "-1234567890");
    }

    @Test
    public void testArrays() {
        valid(List.of(), "[]");
        valid(List.of(), " [ ] ");
        invalid("  \"hello\"\t, \ttrue , false ]");
        invalid(" [ \"hello\"\t, \ttrue , false ");
        invalid("[  \"hello\"\t, \ttrue  false ]");
        valid(List.of(true, false), "[  true, false]");
        valid(List.of("hello", true, false), "[\"hello\",true,false]");
        valid(List.of("hello", true, false), "[  \"hello\" \t, \ttrue , false ] ");
    }

    @Test
    public void testNestedArrays() {
        valid(List.of(List.of(List.of())), "[[[]]]");
        valid(List.of(List.of(List.of(1), true), List.of()), "[[[1], true], []]");

    }


    @Test
    public void testObjects() {
        valid(Map.of(), "{}");
        valid(Map.of(), " { } ");
        valid(Map.of("test", true),
                "{\"test\":true}"
        );
        valid(Map.of("test", true),
                "{  \"test\"  : true  }"
        );
        valid(Map.of("test", true, "hello", false),
                "{\"test\":true,\"hello\":false}"
        );
        valid(Map.of("test", true, "hello", false),
                "   {    \"test\"   :  true  ,  \"hello\"   :  false  } "
        );
        invalid("       \"test\"   :  true  ,  \"hello\"   :  false  } "
        );
        invalid("   {    \"test\"   :  true  ,  \"hello\"   :  false   ");
        invalid("   {    \"test\"     true  ,  \"hello\"   :  false }  ");
        invalid("   {    \"test\"   :  true    \"hello\"   :  false  } ");
    }

    @Test
    public void testNestedObjects() {
        invalid("{{{}}}");
        valid(Map.of("k1", Map.of("k2", Map.of(), "k3", 1)),
                " {\"k1\": {\"k2\" : {}, \"k3\": 1} } ");

    }
}
