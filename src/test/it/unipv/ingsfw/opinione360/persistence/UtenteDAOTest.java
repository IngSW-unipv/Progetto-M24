package test.it.unipv.ingsfw.opinione360.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

public class UtenteDAOTest {

	UtenteDAO utenteDao;
	
    @Before
    public void inTest(){
    	utenteDao = new UtenteDAO();
    	utenteDao.setMode(false);
    	utenteDao.reset();
   }
    
    @Test
    public void resetTestOK() throws SQLException {
    	utenteDao.reset();
    	ArrayList<Utente> results;

		results = utenteDao.selectAll();
		assertTrue(results.isEmpty());
 	
    }
    
    @Test
    public void insertByNameTestOK() {
    	Utente admin = new Utente("Giacomo", "pw");
    	
    	assertTrue(utenteDao.insertUtente(admin));
    }
    
    @Test
    public void insertByNameTestKO() {
    	Utente admin_noname = new Utente("", "pw");
    	
    	assertFalse(utenteDao.insertUtente(admin_noname));
    	
    	Utente user_bad_email = new Utente("admin1", "pw");
    	user_bad_email.setEmail("test#domain.it");
    	
    	assertFalse(utenteDao.insertUtente(user_bad_email));
    }
    
    @Test
    public void selectAllTestOK() throws SQLException {
    	Utente user1 = new Utente("admin1", "pw1");
    	Utente user2 = new Utente("admin2", "pw2");
    	Utente user3 = new Utente("admin3", "pw3");
    	
    	utenteDao.insertUtente(user1);
    	utenteDao.insertUtente(user2);
    	utenteDao.insertUtente(user3);
    	
    	
    	ArrayList<Utente> actual_user_list = new ArrayList<>();
    	
    	actual_user_list.add(user1);
    	actual_user_list.add(user2);
    	actual_user_list.add(user3);
    	    	
    	ArrayList<Utente> retrieved_user_list = utenteDao.selectAll();
    	
    	assertTrue(retrieved_user_list.containsAll(actual_user_list));
    }
    
    @Test
    public void selectByUserPwTestOK() throws SQLException, UserNotFoundException, UserNotFoundException {
    	Utente user = new Utente("admin1", "pw1");
    	
    	utenteDao.insertUtente(user);
    	
    	Utente db_user = utenteDao.selectByUsrPw(user);

    	assertTrue(user.equals(db_user));
    }
    
    @Test
    public void selectTestKO() throws SQLException {
    	Utente user1 = new Utente("admin1", "pw1");
    	
    	utenteDao.insertUtente(user1);
    	
    	user1.setPassword("pw2");

    	 assertThrows(UserNotFoundException.class, () -> utenteDao.selectByUsrPw(user1));
    }
    
    @Test
    public void injectTestKO() throws SQLException {
    	Utente user1 = new Utente(UUID.fromString("1ed60a69-a640-4e99-9043-cb131b0d4720"), "admin1", "pw1");
    	Utente faulty_user = new Utente(UUID.fromString("1ed60a69-a640-4e99-9043-cb131b0d4720"), "admin1", "pw1' or true or '");
    	
    	utenteDao.insertUtente(user1);
    	assertThrows(UserNotFoundException.class, () -> utenteDao.selectByUsrPw(faulty_user));
    }
}
