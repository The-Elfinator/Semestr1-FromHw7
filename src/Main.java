import markup.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        Paragraph paragraph1 = new Paragraph(List.of(
                new Text("1235")
        ));
        paragraph1.toHtml(s);
        System.out.println(s);
    }

}
