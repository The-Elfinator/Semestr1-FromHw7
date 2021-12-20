// Сильное выделение. Копипасту убрать

package md2html.markup;

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
        super.toHtml(s, "strong");
    }

}
