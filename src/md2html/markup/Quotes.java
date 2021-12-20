package md2html.markup;

import java.util.List;

public class Quotes extends AbstractMarker {

    public Quotes(List<Markers> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "''");
    }

    @Override
    public void toHtml(StringBuilder s) {
        super.toHtml(s, "q");
    }
}
