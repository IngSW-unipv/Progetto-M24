package test.it.unipv.ingsfw.opinione360.persistence;

import static org.junit.Assert.*;

import java.security.Timestamp;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.exception.UserMissingAccessException;
import it.unipv.ingsfw.opinione360.model.AreaOpzione;
import it.unipv.ingsfw.opinione360.model.ContenutoTesto;
import it.unipv.ingsfw.opinione360.model.IContenuto;
import it.unipv.ingsfw.opinione360.model.IOpzione;
import it.unipv.ingsfw.opinione360.model.Opzione;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.AreaOpzioneDAO;
import it.unipv.ingsfw.opinione360.persistence.ContenutoDAO;
import it.unipv.ingsfw.opinione360.persistence.SondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;

public class SondaggioDAOTest {
	static private ContenutoDAO cdao;
	static private SondaggioDAO sdao;
	static private AreaOpzioneDAO adao;
	static private UtenteDAO udao;
	
	private Sondaggio sond;
	private static Utente votante1;
	private static Utente votante2;
	private static Utente votante3;
	
	private static Utente candidato;
	
	IContenuto contenuto;
	IContenuto contenuto2;
	static Opzione opzione1;
	static Opzione opzione2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws SQLException {
		sdao = new SondaggioDAO();
		sdao.setMode(false);
		sdao.reset();
		
		adao = new AreaOpzioneDAO();
		adao.setMode(false);
		adao.reset();
		
		udao = new UtenteDAO();
		udao.setMode(false);
		udao.reset();
		
		votante1=new Utente("testUser1", "pw1");
		votante2=new Utente("testUser2", "pw2");
		votante3=new Utente("testUser3", "pw3");
		candidato=new Utente("testCandidate", "pw");
		
		udao.insertUtente(votante1);
		udao.insertUtente(votante2);
		udao.insertUtente(votante3);
		udao.insertUtente(candidato);
		
		opzione1 = new Opzione(0, "Opzione1 test");
		opzione2 = new Opzione(1, "Opzione2 test");		
	}

	@Before
	public void setUp(){
		sdao.reset();
		ArrayList<Utente> votanti = new ArrayList<>();
		votanti.add(votante1);
		votanti.add(votante2);
		votanti.add(votante3);
		Calendar start_date = Calendar.getInstance();
		Calendar close_date = Calendar.getInstance();
		
		start_date.setTimeInMillis(System.currentTimeMillis()-10);
		close_date.setTimeInMillis(System.currentTimeMillis()+100000);
		
		sond=new Sondaggio("Quesito di test", votanti, start_date, close_date);
		
		sond.addOpzione(opzione1, candidato);
		sond.addOpzione(opzione2);
		
		contenuto=new ContenutoTesto("testo per l'opzione 1");
		contenuto2=new ContenutoTesto("testo per l'opzione 2");
		
		sond.caricaContenuto(opzione1, contenuto);
		sond.caricaContenuto(opzione1, contenuto2);
	}

	@Test
	public void resetOK() throws SQLException {
		sdao.insertSondaggio(sond);
		sdao.reset();
		assertEquals(sdao.selectAll().size(), 0);
	}
	
	@Test
	public void insert_select_OK() throws SQLException {
		sdao.insertSondaggio(sond);

		Sondaggio s_down = sdao.selectById(sond);
		
		assertTrue(sond.getId().equals(s_down.getId()));
		
		assertEquals(sond.getQuesito(), s_down.getQuesito());
		
		assertArrayEquals(sond.getAllOpzioni(), s_down.getAllOpzioni()); //verify same id
		
		assertEquals(sond.getData_apertura().DAY_OF_YEAR, s_down.getData_apertura().DAY_OF_YEAR);
		
		assertArrayEquals(sond.getOpzioni(), s_down.getOpzioni()); //verify same description
		
		assertTrue(sond.getContenuti(opzione1).containsAll(s_down.getContenuti(opzione1)));
	}
	
	@Test
	public void VotaUpdateOK() throws SQLException {
		sdao.insertSondaggio(sond);

		sond.vota(opzione1, votante1);
		sond.vota(opzione1, votante2);
		sond.vota(opzione2, votante3);
		
		assertTrue(sdao.updateVotoSondaggio(sond));
		Sondaggio s_down = sdao.selectById(sond);
		
		for (Opzione o: sond.getAllOpzioni()) {
			assertEquals(sond.getRisultato(o), s_down.getRisultato(o));
		}
	}
	
	@Test
	public void caricaVetrinaOK() throws SQLException {
		sdao.insertSondaggio(sond);
		IContenuto contenuto3 = new ContenutoTesto("Altro testo");
		IContenuto contenuto4 = new ContenutoTesto("Altro testo ancora");
		
		sond.caricaContenuto(opzione2, contenuto3);
		sond.caricaContenuto(opzione2, contenuto4);
		
		assertTrue(sdao.insertContenutiSondaggio(sond, opzione2));
		
		Sondaggio s_down = sdao.selectById(sond);
		
		assertTrue(s_down.getContenuti(opzione2).contains(contenuto3));
		assertTrue(s_down.getContenuti(opzione2).contains(contenuto4));		
	}
}
