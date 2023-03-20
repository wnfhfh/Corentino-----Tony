package _tests;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import structures.pile.Pile;

class PileTest {
    /*420-202 – Quiz 4
        Groupe 1 (lundi, mercredi)
        Nom : Houde, Antoine; 2235325
*/
    Pile p = new Pile();

    @Test
    void push_AjouteLeBonElement() {

        p.push(1);
        assertEquals(1, p.peek());
        p.push(2);
        assertEquals(2, p.peek());
    }

    @Test
    void pop_RetourneLElementDuDessusRetire() {

        p.push(1);
        p.push(2);
        assertEquals(2, p.pop());
        assertEquals(1, p.size());

        assertEquals(1, p.pop());
        assertEquals(0, p.size());
    }


    @Test
    void peek_RetourneLElementDuDessusSansLEffacer() {

        p.push(1);
        p.push(2);

        assertEquals(2, p.peek());
        assertEquals(2, p.peek());

        p.pop();
        assertEquals(1, p.peek());

        p.pop();
    }

    @Test
    void empty_RetourneVraiSiPileVide() {

        assertEquals(true, p.empty());

        p.push(1);
        assertEquals(false, p.empty());

        p.pop();
        assertEquals(true, p.empty());
    }

    @Test
    void size_RetourneNbDElementsDansLaPile() {

        assertEquals(0, p.size());

        p.push(1);
        assertEquals(1, p.size());

        p.push(1);
        assertEquals(2, p.size());
    }
}