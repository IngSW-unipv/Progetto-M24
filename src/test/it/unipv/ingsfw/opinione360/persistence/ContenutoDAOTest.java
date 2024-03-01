package test.it.unipv.ingsfw.opinione360.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.exception.UserMissingAccessException;
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
import it.unipv.ingsfw.opinione360.persistence.exception.InvalidFormatException;

public class ContenutoDAOTest {
	static private ContenutoDAO cdao;
	static private SondaggioDAO sdao;
	static private AreaOpzioneDAO adao;
	static private UtenteDAO udao;
	
	private static Sondaggio sond;
	private static Utente votante;
	private static Utente candidato;
	
	IContenuto contenuto;
	static Opzione opzione;
	
	@BeforeClass
	public static void setUpBeforeClass() throws SQLException {
		cdao = new ContenutoDAO();
		cdao.setMode(false);
		cdao.reset();
		
		sdao = new SondaggioDAO();
		sdao.setMode(false);
		sdao.reset();
		
		adao = new AreaOpzioneDAO();
		adao.setMode(false);
		adao.reset();
		
		udao = new UtenteDAO();
		udao.setMode(false);
		udao.reset();
		
		votante=new Utente("testUser", "pw1");
		candidato=new Utente("testCandidate", "pw2");
		
		udao.insertUtente(votante);
		udao.insertUtente(candidato);
		
		ArrayList<Utente> votanti = new ArrayList<>();
		votanti.add(votante);
		sond=new Sondaggio("Quesito di test", votanti);
		opzione = new Opzione(0, "Opzione test");
		sond.addOpzione(opzione, candidato);
		
		sdao.insertSondaggio(sond);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		cdao.reset();
		sdao.reset();
		adao.reset();
		udao.reset();
	}
	@Before
	public void setUp(){
		cdao.reset();
		contenuto=new ContenutoTesto("testo");
		
	}

	@Test
	public void resetOK() throws SQLException {
		cdao.insertContenuto(contenuto, new Opzione(0), sond);
		cdao.reset();
		assertEquals(cdao.selectAll().size(), 0);
	}
	
	@Test
	public void insert_selectOK() throws SQLException {
		cdao.insertContenuto(contenuto, opzione, sond);
		ArrayList<IContenuto> c_select = cdao.selectByOpzione(opzione, sond);
		assertEquals(c_select.size(), 1);
		
		assertArrayEquals(c_select.get(0).getData(), contenuto.getData());
	}
	
	@Test
	public void insertNOAccessKO() throws SQLException {
		assertFalse(cdao.insertContenuto(contenuto, votante, sond));
	}
	
	@Test
	public void insertBadOptionKO() throws SQLException {
		assertFalse(cdao.insertContenuto(contenuto, new Opzione(4), sond));
	}
}
