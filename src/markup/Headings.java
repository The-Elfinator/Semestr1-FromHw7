package markup;

import java.util.List;

public class Headings extends AbstractMarker {

    private final int level;

    public Headings(List<Markers> list, int level) {
        super(list);
        this.level = level;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "");
    }

    @Override
    public void toHtml(StringBuilder s) {
        super.toHtml(s, "h" + level);
    }
}
