package test.it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.AreaCandidato;
import it.unipv.ingsfw.opinione360.model.Vetrina;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.*;

public class VetrinaTest {

    Vetrina v;
    Utente u1;
    Utente u2;
    Utente u3;


    @Before
    public void inTest(){
        u1 = new Utente("n", "o");
		u2 = new Utente("g", "e");
		u3 = new Utente("n", "o");
        ArrayList<Utente> c = new ArrayList<>();
		c.add(u1);
        c.add(u2);
        v = new Vetrina(c);
    }

    @Test
    public void getAreaTestEq1() {
        assertThrows(UserMissingAccessException.class, () -> v.getArea(u3));
    }

    @Test
    public void getAreaTestEq2() {
        AreaCandidato ac = v.getArea(u1);
        assertNotNull(ac);
    }

}