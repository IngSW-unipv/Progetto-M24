package it.unipv.ingsfw.opinione360.server;

import com.sun.net.httpserver.HttpServer;
import it.unipv.ingsfw.opinione360.handler.*;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * Classe Singleton che utilizza il server http presente nelle API Java
 * @see it.unipv.ingsfw.opinione360.handler
 * @see IServer
 */
public class ServerSingleton implements IServer {

    private static ServerSingleton istance = null;
    private int porta;
    private HttpServer server;
    private int backlog;


    private ServerSingleton(){
        try {
            init();
            server = HttpServer.create(new InetSocketAddress(porta),backlog);
        } catch (Exception e) {
            System.out.println("Errore nella creazione del server.");
            e.printStackTrace();
        }
    }

    /**
     * Metodo per restituire un istanza della classe server singleton
     * @return istance
     */
    public static ServerSingleton getIstance(){
        if(istance == null){
            istance = new ServerSingleton();
        }
        return istance;
    }

    private void init(){
        final String PORT_NUMBER = "port.number";
        final String BACKLOG = "backlog";
        try {
            Properties p = new Properties(System.getProperties());
            p.load(new FileInputStream("Server/properties/properties.properties"));
            porta = Integer.parseInt(p.getProperty(PORT_NUMBER));
            backlog = Integer.parseInt(p.getProperty(BACKLOG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che crea i Context al server e lo avvia
     * I Context disponibili sono:<br>
     * /Signup<br>
     * /Login<br>
     * /Vota<br>
     * /Consultazioni_lista<br>
     * /Consultazione<br>
     * /GetUtenti<br>
     * /Crea_consultazione<br>
     * /Carica_vetrina<br>
     */
    @Override
    public void startServer(){
        PostMethodFilter postFilter = new PostMethodFilter();
        GetMethodFilter getFilter = new GetMethodFilter();

        server.createContext("/Signup", new SignupHandler()).getFilters().add(postFilter);
        server.createContext("/Login", new LoginHandler()).getFilters().add(postFilter);
        server.createContext("/Vota", new VotoHandler()).getFilters().add(postFilter);
        server.createContext("/Consultazioni_lista", new GetConsultazioniHandler()).getFilters().add(getFilter);
        server.createContext("/Consultazione", new GetConsultazioneSingolaHandler()).getFilters().add(getFilter);
        server.createContext("/GetUtenti", new GetUtentiHandler()).getFilters().add(getFilter);
        server.createContext("/Crea_consultazione", new CreaConsultazioneHandler()).getFilters().add(postFilter);
        server.createContext("/Carica_vetrina", new CaricaVetrinaHandler()).getFilters().add(postFilter);

        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server in attesa sulla porta " + porta);
    }

    /**
     * Metodo che permette di spegnere il server
     */
    @Override
    public void stopServer(){
        server.stop(100);
    }

    /**
     * Metodo che permette di spegnere il server specificando il ritardo
     * @param delay il ritardo (in secondi) dopo cui il server si spegne
     */
    @Override
    public void stopServer(int delay){
        server.stop(delay);
    }
}
