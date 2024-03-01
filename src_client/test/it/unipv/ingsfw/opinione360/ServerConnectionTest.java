package test.it.unipv.ingsfw.opinione360;

import it.unipv.ingsfw.opinione360.ServerConnection;
import it.unipv.ingsfw.opinione360.exception.*;
import it.unipv.ingsfw.opinione360.model.*;
import it.unipv.ingsfw.opinione360.persistence.AmministratoreDAO;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.*;

public class ServerConnectionTest {
    ServerConnection srvConn = new ServerConnection();
    IUtenteC user;
    IUtenteC userOut;
    @Before
    public void init(){
        user = new UtenteC("Test","test1@test.it", "12345", "TEST!" );
    }

    @Test
    public void signupTestOK() throws ServerException, IOException, AlreadyRegisteredUserException {
       UtenteC userOut = srvConn.signup(user);
       assertNotNull(userOut);
       assertEquals(user.getUsername(),userOut.getUsername());
    }

    @Test
    public void signupTestKO2() throws ServerException, IOException, AlreadyRegisteredUserException {
        assertThrows(AlreadyRegisteredUserException.class, ()->srvConn.signup(user));
    }

    @Test
    public void loginTestOK() throws ServerException, IOException {
        userOut = srvConn.login(user);
        assertNotNull(userOut);
        assertEquals(user.getUsername(),userOut.getUsername());
    }

    @Test
    public void loginTestKO(){
        IUtenteC fakeUser = new UtenteC("1","2");
        assertThrows(UserNotFoundException.class, ()-> srvConn.login(fakeUser));
    }

    @Test
    public void getUtentiTestKO() {
        UtenteC fakeAdmin = new AmministratoreC(UUID.randomUUID());
        assertThrows(UserNotFoundException.class, ()-> srvConn.getUtenti(fakeAdmin));
    }
    @Test
    public void getUtentiTestKO2() {
        IUtenteC notAdmin = new UtenteC(UUID.randomUUID());
        assertThrows(UserNotFoundException.class, ()-> srvConn.getUtenti(notAdmin));
    }
    @Test
    public void getUtentiTestOK() throws IOException, SQLException, ServerException {
        IUtenteC u = new UtenteC("Administrator", "prova@op.it" ,"pass2", "1234");
        IUtenteC admin = srvConn.login(u);
        ArrayList<UtenteC> user = srvConn.getUtenti(admin);
        assertNotNull(user);
    }

    @Test
    public void creaConsultazioneTestOK() throws IOException, SQLException, ServerException {
        IUtenteC u = new UtenteC("Administrator", "prova@op.it" ,"pass2", "1234");
        IUtenteC admin = srvConn.login(u);
        IConsultazioneC sondaggio = new SondaggioC("Prova", new ArrayList<>(), new ArrayList<>(), new GregorianCalendar(),new GregorianCalendar());
        String risposta = srvConn.creaConsultazione(admin, sondaggio);
        assertEquals("Consultazione creata con successo", risposta);
    }

     @Test
    public void creaConsultazioneTestKO() throws IOException, SQLException, ServerException {
        IUtenteC fakeUser = new UtenteC("Test", "test@op.it" ,"pass2", "12");
        IConsultazioneC sondaggio = new SondaggioC("Prova", new ArrayList<>(), new ArrayList<>(), new GregorianCalendar(),new GregorianCalendar());
        assertThrows(UserMissingAccessException.class, () -> srvConn.creaConsultazione(fakeUser, sondaggio));
    }

    @Test
    public void getConsultazioneTestOK() throws ServerException, IOException {
        IConsultazioneC c = new SondaggioC(UUID.randomUUID());
        assertNotNull(srvConn.getConsultazione(c));
    }

    @Test
    public void caricaVetrinaTestOK() throws ServerException, IOException {
        IConsultazioneC c = new SondaggioC(UUID.randomUUID());
        IContenutoC co = new ContenutoTestoC();
        co.fromFile(new File("test.txt"));
        assertNotNull(srvConn.caricaVetrina(user, c, co, "txt" ));
    }
    @Test
    public void votaTestKO() throws ServerException, IOException {
        IConsultazioneC c = new SondaggioC(UUID.randomUUID());
        OpzioneC[] opz = new OpzioneC[]{new OpzioneC(1)};
        assertThrows(ConsultationExpiredException.class, () -> srvConn.vota(user, opz, c));
    }

    @Test
    public void votaTestOK() throws ServerException, IOException {
        IConsultazioneC c = new SondaggioC(UUID.randomUUID());
        OpzioneC[] opz = new OpzioneC[]{new OpzioneC(1)};
        assertThrows(ConsultationExpiredException.class, () -> srvConn.vota(user, opz, c));
    }
}
