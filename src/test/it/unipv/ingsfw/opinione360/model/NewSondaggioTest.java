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

public class NewSondaggioTest {

    Sondaggio cons;
    Sondaggio consScaduta;
    Utente u1;
    Utente u2;
    Utente u3;
    Utente u4;
    Utente amm;


    @Before
    public void inTest() throws InterruptedException{
        u1 = new Utente("n", "o");
		u2 = new Utente("g", "e");
		u3 = new Utente("n", "o");
		u4 = new Utente("g", "e");
		
		amm = new Amministratore("g", "e");
		
		ArrayList<Utente> votanti = new ArrayList<>();
		votanti.add(u1);
		votanti.add(u2);
        votanti.add(u3);
        votanti.add(u4);
        
        cons = new Sondaggio("Chi e' il miglior pilota di Formula 1?", votanti);
        cons.addOpzione(new Opzione("Michael Schumacher"), u4);
        cons.addOpzione(new Opzione("Lewis Hamilton"));
        cons.addOpzione(new Opzione("Ayrton Senna"));
        cons.addOpzione(new Opzione("Sebastian Vettel"));
        cons.addOpzione(new Opzione("Alain Prost"));
        
        Calendar start_date = new GregorianCalendar(2024, Calendar.JANUARY,1, 0, 0,0);
        consScaduta = new Sondaggio("Miglior pilota di Formula 1?", new ArrayList<AreaOpzione>(), votanti, start_date, new GregorianCalendar());
        
        Thread.sleep(1); //evito errore per consultazione non ancora aperta
    }

    @Test
    public void votaTestNoAccessKO() {
        assertThrows(UserMissingAccessException.class, () -> cons.vota(new Opzione(1), amm));
    }

    @Test
    public void votaTestAfterCloseKO() {
        assertThrows(ConsultationExpiredException.class, () -> consScaduta.vota(new Opzione(1), u1));
    }

    @Test
    public void votaTestIllegalOptionKO() {
        assertThrows(OptionNotFoundException.class, () -> cons.vota(new Opzione(5),u3));
        assertThrows(OptionNotFoundException.class, () -> cons.vota(new Opzione(-1),u3));
    }

    @Test
    public void votaTestDoppioVotoKO() {
        cons.vota(new Opzione(3),u2);
        assertThrows(UserMissingAccessException.class, () -> cons.vota(new Opzione(3),u2));
    }

    @Test
    public void votaTestCountOK() {
        cons.vota(new Opzione(1),u1);
        cons.vota(new Opzione(1),u2);
        cons.vota(new Opzione(4),u3);
        cons.vota(new Opzione(3),u4);
        
        assertEquals(cons.getRisultato(new Opzione(1)), 2);
        assertEquals(cons.getRisultato(new Opzione(3)), 1);
        assertEquals(cons.getRisultato(new Opzione(4)), 1);  
    }

}