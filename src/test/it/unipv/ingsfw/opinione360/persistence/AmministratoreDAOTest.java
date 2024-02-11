package test.it.unipv.ingsfw.opinione360.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.persistence.AmministratoreDAO;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

public class AmministratoreDAOTest {
	AmministratoreDAO adminDao;
	
    @Before
    public void inTest(){
    	adminDao = new AmministratoreDAO();
    	adminDao.reset();
   }
    
    @Test
    public void resetTestOK() throws SQLException {
    	adminDao.reset();
    	ArrayList<Amministratore> results;

		results = adminDao.selectAll();
		assertTrue(results.isEmpty());
 	
    }
    
    @Test
    public void insertByNameTestOK() {
    	Amministratore admin = new Amministratore("Giacomo", "pw");
    	
    	assertTrue(adminDao.insertAmministratore(admin));
    }
    
    @Test
    public void insertByNameTestKO() {
    	Amministratore admin_noname = new Amministratore("", "pw");
    	
    	assertFalse(adminDao.insertAmministratore(admin_noname));
    	
    	Amministratore admin_bad_email = new Amministratore("admin1", "pw");
    	admin_bad_email.setEmail("test#domain.it");
    	
    	assertFalse(adminDao.insertAmministratore(admin_bad_email));
    }
    
    @Test
    public void selectAllTestOK() throws SQLException {
    	Amministratore admin1 = new Amministratore("admin1", "pw1");
    	Amministratore admin2 = new Amministratore("admin2", "pw2");
    	Amministratore admin3 = new Amministratore("admin3", "pw3");
    	
    	adminDao.insertAmministratore(admin1);
    	adminDao.insertAmministratore(admin2);
    	adminDao.insertAmministratore(admin3);
    	
    	
    	ArrayList<Amministratore> actual_admin_list = new ArrayList<>();
    	
    	actual_admin_list.add(admin1);
    	actual_admin_list.add(admin2);
    	actual_admin_list.add(admin3);
    	    	
    	ArrayList<Amministratore> retrieved_admin_list = adminDao.selectAll();
    	
    	assertTrue(retrieved_admin_list.containsAll(actual_admin_list));
    }
    
    @Test
    public void selectByUserPwTestOK() throws SQLException, UserNotFoundException, it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException {
    	Amministratore admin1 = new Amministratore("admin1", "pw1");
    	
    	adminDao.insertAmministratore(admin1);
    	
    	Amministratore db_admin = adminDao.selectByUserPw(admin1);

    	assertTrue(admin1.equals(db_admin));
    }
    
    @Test
    public void selectTestKO() throws SQLException {
    	Amministratore admin1 = new Amministratore("admin1", "pw1");
    	
    	adminDao.insertAmministratore(admin1);
    	
    	admin1.setPassword("pw2");

    	 assertThrows(UserNotFoundException.class, () -> adminDao.selectByUserPw(admin1));
    }
    
    @Test
    public void injectTestKO() throws SQLException {
    	Amministratore admin1 = new Amministratore(UUID.fromString("1ed60a69-a640-4e99-9043-cb131b0d4720"), "admin1", "pw1");
    	Amministratore faulty_admin1 = new Amministratore(UUID.fromString("1ed60a69-a640-4e99-9043-cb131b0d4720"), "admin1", "pw1' or true or '");
    	
    	adminDao.insertAmministratore(admin1);
    	assertThrows(UserNotFoundException.class, () -> adminDao.selectByUserPw(faulty_admin1));
    }
}
