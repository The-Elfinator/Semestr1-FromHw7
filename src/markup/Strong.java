// Сильное выделение. Копипасту убрать

package markup;

import java.util.List;

public class Strong extends AbstractMarker {

    public Strong(List<Markers> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder s) {

        super.toMarkdown(s, "__");

    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<strong>");
        super.toHtml(s);
        s.append("</strong>");
    }

}
