package structures.pile;

import java.io.Serializable;

public class NoeudPile implements Serializable {
    private Object element;
    private NoeudPile precedent = null;

    public NoeudPile(Object o) {
        element = o;
    }

    public NoeudPile(Object o, NoeudPile n) {
        setPrecedent(n);
        element = o;
    }

    public NoeudPile getPrecedent() {
        return precedent;
    }

    public void setPrecedent(NoeudPile precedent) {
        this.precedent = precedent;
    }

    public Object getElement() {
        return element;
    }
}
