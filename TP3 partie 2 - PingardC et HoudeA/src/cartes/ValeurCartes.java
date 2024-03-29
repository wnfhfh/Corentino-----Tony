package cartes;

import java.io.Serializable;

public enum ValeurCartes implements Serializable {
    V_AS("A", 14),
    V_2("2", 2),
    V_3("3", 3),
    V_4("4", 4),
    V_5("5", 5),
    V_6("6", 6),
    V_7("7", 7),
    V_8("8", 8),
    V_9("9", 9),
    V_10("10", 10),
    V_JACK("J", 11),
    V_QUEEN("Q", 12),
    V_KING("K", 13);

    public String symbole;
    private int valeurDecarte;

    ValeurCartes(String symbole, int valeur) {
        this.symbole = symbole;
        this.valeurDecarte = valeur;

    }

    public int getValeurDecarte() {
        return valeurDecarte;
    }
}
