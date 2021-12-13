import expression.*;
import mypackage.Json;

public class Main {

    public static void main(String[] args) {
        Negate negate = new Negate(new Add(new Variable("x"), new Const(2)));
//        System.out.println(negate.evaluate(0, 0, 0));
        System.out.println(Json.parse("[4, 5, 6]"));
        //System.out.println(new Const(2).evaluate(0, 0, 0));
        //System.out.println(add.evaluate(0, 0, 0));

    }
}
