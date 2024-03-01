package it.unipv.ingsfw.opinione360.server;

/**
 * Interfaccia implementata da tutte le classi che vogliono svolgere la funzione di server
 * @see ServerSingleton
 * */
public interface IServer {
    void startServer();

    void stopServer();
    void stopServer(int delay);
}
