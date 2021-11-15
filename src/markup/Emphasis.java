// Выделение. Копипасту убрать :(

package markup;

import java.util.List;

public class Emphasis extends AbstractMarker {

    public Emphasis(List<Markers> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
//        s.append("*");
        super.toMarkdown(s, "*");
//        s.append("*");
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<em>");
        super.toHtml(s);
        s.append("</em>");
    }

}
