// Абзац

package markup;

import java.util.List;

public class Paragraph extends AbstractMarker {

    public Paragraph(List<Markers> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "");
    }
}
