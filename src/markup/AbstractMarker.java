// Использовать для общих методов \(^_^)/
// ?Добавить protected функцию которая принимает StringBuilder и String и кастует String + StringBuilder.toMarkdown + String
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
            StringBuilder s1 = new StringBuilder();
            obj.toMarkdown(s1);
            s.append(s1);
        }
    }

    @Override
    public void toHtml(StringBuilder s) {
        for (Markers obj : list) {
            StringBuilder s1 = new StringBuilder();
            obj.toHtml(s1);
            s.append(s1);
        }
    }
    
}
