package test.it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.*;
import it.unipv.ingsfw.opinione360.model.exception.ConsultationExpiredException;
import it.unipv.ingsfw.opinione360.model.exception.OptionNotFoundException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class SondaggioTest {

    Sondaggio cons;
    Sondaggio consScaduta;
    Utente u1;
    Utente u2;
    Utente u3;
    Utente u4;
    Utente amm;


    @Before
    public void inTest(){
        u1 = new Utente("n", "o");
		u2 = new Utente("g", "e");
		u3 = new Utente("n", "o");
		u4 = new Utente("g", "e");
		amm = new Amministratore("g", "e");
		ArrayList<Utente> v = new ArrayList<>();
		v.add(u1);
		v.add(u2);
        v.add(u3);
        v.add(u4);
		ArrayList<Utente> c = new ArrayList<>();
		c.add(u4);
		String[] o = {"Michael Schumacher", "Lewis Hamilton", "Ayrton Senna", "Sebastian Vettel", "Alain Prost"};
        Calendar data = new GregorianCalendar(2024, Calendar.FEBRUARY,30, 0, 0,0);
        cons = new Sondaggio("Miglior pilota di Formula 1?", v, c, o, data);
        Calendar data2 = new GregorianCalendar(2024, Calendar.JANUARY,1, 0, 0,0);
        consScaduta = new Sondaggio("Miglior pilota di Formula 1?", v, c, o, data2);
    }

    @Test
    public void votaTestEq1() {

        assertThrows(UserMissingAccessException.class, () -> cons.vota(1, amm));

    }

    @Test
    public void votaTestEq2() {

        assertThrows(ConsultationExpiredException.class, () -> consScaduta.vota(1, u1));

    }

    @Test
    public void votaTestEq3() {


        assertThrows(OptionNotFoundException.class, () -> cons.vota(5,u3));
        assertThrows(OptionNotFoundException.class, () -> cons.vota(-1,u3));

    }

    @Test
    public void votaTestEq4() {
        cons.vota(3,u2);
        assertThrows(UserMissingAccessException.class, () -> cons.vota(3,u2));
    }

     @Test
    public void votaTestEq5() {
        cons.vota(1,u1);
        cons.vota(1,u2);
        //int [] risultati = cons.getRisultati();
       // assertEquals(2, risultati[1]);
    }

    @Test
    public void votaTestEq6() {
        int [] check = {0, 2, 0, 1, 1};
        cons.vota(1,u1);
        cons.vota(1,u2);
        cons.vota(4,u3);
        cons.vota(3,u4);
       // int [] risultati = cons.getRisultati();
        //assertArrayEquals(check, risultati);

    }

}