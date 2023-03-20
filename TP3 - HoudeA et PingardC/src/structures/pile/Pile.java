package structures.pile;

public class Pile {
    private NoeudPile sommet;
    private int taille = 0;

    public Pile() {
    }

    public void push(Object o) {
        if (taille == 0) {
            sommet = new NoeudPile(o);
        } else {
            sommet = new NoeudPile(o, sommet);
        }

        taille++;
    }

    public Object pop() {
        Object popped = sommet.getElement();

        sommet = sommet.getPrecedent();

        taille--;

        return popped;
    }

    public Object peek() {
        return sommet.getElement();
    }

    public boolean empty() {
        return taille == 0;
    }

    public int size() {
        return taille;
    }
}
