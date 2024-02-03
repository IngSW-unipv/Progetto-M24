package it.unipv.ingsfw.opinione360.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * Questa classe permette la gestione di un HttpExchange di votazione
 * Accetta un HTTPExchange che abbia la segeuente struttura del body:<br>
 * <i>id scelta id</i> <br>
 * dove id è l'id dell'utente, scelta la sua scelta e id l'id della consultazione in cui sta votando
 * @see com.sun.net.httpserver.HttpHandler
 */
public class VotoHandler implements HttpHandler {

    private String messaggio;
    private String risposta;

    /**
     * Costruttore
     */
    public VotoHandler(){
        messaggio = "";
    }

    /**
     * Override del metodo dell'interfaccia HttpHandler che gestisce una votazione
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try{
            String righe;
            exchange.getRequestMethod();
            InputStream in = exchange.getRequestBody();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(in));
            while((righe = in1.readLine()) != null){
                messaggio += righe;
            }

            String [] msg = messaggio.split("\\s");
            System.out.println(msg[0]);
            System.out.println(msg[1]);
            System.out.println(msg[2]);


            Utente u = new Utente(UUID.fromString(msg[0]));
            Sondaggio c = new Sondaggio();
            int scelta = Integer.parseInt(msg[1]);

            //utilizzabili solo quando ci sara il DB
            //c1 = c.selectConsultazione(cons);
            //c1.vota(scelta, u);
            //Sondaggio s2 = new Sondaggio();
            //Utente user = IUtenteDAO.selectUtente(u);
            //if(user == null)
            //  throw UnknownUserException();


            //Codice per far funzionare tutto il sistema
            c.vota(scelta, u);
            risposta = "Voto acquisito correttamente";
            exchange.sendResponseHeaders(200, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (UserMissingAccessException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(401, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }catch (IOException exc){
            exc.printStackTrace();

        }
    }
}