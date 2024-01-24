package it.unipv.ingsfw.opinione360.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Opinione360App{

	public static void main(String[] args) {
		Utente u1 = new Utente("Davide Valsecchi", "DV11");
		Utente u2 = new Utente("Carlo Vanzini", "CV01");
		Utente u3 = new Utente("Marc Genè", "MC09");
		Utente u4 = new Utente("Matteo Bobbi", "MB03");
		Utente u5 = new Utente("Jean Todt", "JT04");
		Utente u6 = new Utente("Toto Wolff", "TW20");
		Utente u7 = new Amministratore("SkySport", "SP24");
		ArrayList<Utente> votanti = new ArrayList<>();
		votanti.add(u1);
		votanti.add(u2);
		votanti.add(u3);
		votanti.add(u4);
		ArrayList<Utente> candidati = new ArrayList<>();
		candidati.add(u5);
		candidati.add(u6);
		String[] opzioni = {"Michael Schumacher", "Lewis Hamilton", "Ayrton Senna", "Sebastian Vettel", "Alain Prost"};
		Sondaggio sondaggio = new Sondaggio("Miglior pilota di Formula 1?", votanti, candidati, opzioni, Date.from(Instant.now()));
		
		sondaggio.caricaContenuto(u6, "Nonostante la macchina inferiore sta dimostrando di essere ancora il miglior pilota al mondo.\nVotate per Lewis Hamilton!");
		sondaggio.caricaContenuto(u5, "Nessun altro al mondo è riuscito a vincere 5 mondiali di fila.\nVotate per Michael Schumacher!");
		
		sondaggio.stampaVetrina();
		
		sondaggio.vota(0, u1);
		sondaggio.vota(1, u2);
		sondaggio.vota(0, u3);
		sondaggio.vota(2, u4);
		sondaggio.stampaRisultati();
	}

}
