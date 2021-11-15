import markup.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Paragraph paragraph = new Paragraph(List.of(
                new Emphasis(List.of(
                        new Text("12")
                )),
                new Strikeout(List.of(
                        new Text("34")
                ))
        ));
        StringBuilder s = new StringBuilder();
        paragraph.toMarkdown(s);
        //paragraph.toHtml(s);
        System.out.println(s);
    }

}
