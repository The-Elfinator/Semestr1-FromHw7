package markup;

import java.util.List;

public class Code extends AbstractMarker{

    public Code(List<Markers> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "`");
    }

    @Override
    public void toHtml(StringBuilder s) {
        super.toHtml(s, "code");
    }
}
