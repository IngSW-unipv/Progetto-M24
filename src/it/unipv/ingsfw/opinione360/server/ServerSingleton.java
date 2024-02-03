package it.unipv.ingsfw.opinione360.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Classe Singleton che utilizza il server http presente nelle API Java
 * @see VotoHandler
 * @see IServer
 */
public class ServerSingleton implements IServer {

    private static ServerSingleton istance = null;
    private final int PORTA = 60000;
    private HttpServer server;
    private final int backlog = 10;


    private ServerSingleton(){
        try {
            server = HttpServer.create(new InetSocketAddress(PORTA),backlog);
        } catch (IOException e) {
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

    /**
     * Metodo che crea i Context al server e lo avvia
     * I Context disponibili sono:
     * /Vota
     *
     */
    @Override
    public void startServer(){
        server.createContext("/Vota", new VotoHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server in attesa sulla porta " + PORTA);
    }
    @Override
    public void stopServer(){
        server.stop(100);
    }
}
