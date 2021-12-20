// Просто техт Q('-'Q)

package md2html.markup;

public class Text implements Markers {

    private final String s;

    public Text (String s) {
        this.s = s;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(this.s);
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append(this.s);
    }

}
