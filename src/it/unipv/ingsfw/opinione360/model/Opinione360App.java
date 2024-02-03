package it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.server.IServer;
import static it.unipv.ingsfw.opinione360.server.ServerSingleton.getIstance;

public class Opinione360App{

	public static void main(String[] args) {
		IServer s = getIstance();
		s.startServer();
	}
}
