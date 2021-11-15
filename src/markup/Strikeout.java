// Зачеркивание. Копипасту убрать

package markup;

import java.util.List;

public class Strikeout extends AbstractMarker {

    public Strikeout(List<Markers> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "~");
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<s>");
        super.toHtml(s);
        s.append("</s>");
    }

}
