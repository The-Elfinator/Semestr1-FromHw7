// Использовать для общих методов \(^_^)/
//  Done --- Добавить protected функцию которая принимает StringBuilder и String и кастует String + StringBuilder.toMarkdown + String
package markup;

import java.util.List;

public abstract class AbstractMarker implements Markers {
    protected List<Markers> list;

    public AbstractMarker(List<Markers> list) {
        this.list = list;
    }

    protected void toMarkdown(StringBuilder s, String teg) {
        s.append(teg);
        toMarkdown2(s);
        s.append(teg);
    }

    protected void toMarkdown2(StringBuilder s) {
        for (Markers obj : list) {
            obj.toMarkdown(s);
        }
    }

    protected void toHtml(StringBuilder s, String teg) {
        String openTeg = "<" + teg + ">";
        String closeTeg = "</" + teg + ">";
        s.append(openTeg);
        toHtml2(s);
        s.append(closeTeg);
    }

    public void toHtml2(StringBuilder s) {
        for (Markers obj : list) {
            obj.toHtml(s);
        }
    }

}
