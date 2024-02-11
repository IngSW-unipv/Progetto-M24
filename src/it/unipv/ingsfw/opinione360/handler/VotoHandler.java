package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.model.exception.ConsultationExpiredException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Questa classe permette la gestione di un HttpExchange di votazione
 * Accetta un HTTPExchange che abbia abbia nel body i seguenti oggetti:<br>
 * Utente/nConsultazione/nscelta<br>
 * in cui Utente, Consultazione e scleta sono rappresentati in Json.
 * @see com.sun.net.httpserver.HttpHandler
 */
public class VotoHandler implements HttpHandler {

    private String messaggio;
    private String risposta;

    /**
     * Costruttore
     */
    public VotoHandler(){
        messaggio = null;
        risposta = null;
    }

    /**
     * Override del metodo dell'interfaccia HttpHandler che gestisce una votazione
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        messaggio = null;
        Gson gson = new Gson();

        try{
            InputStream in = exchange.getRequestBody();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(in));

            messaggio = buffread.readLine();
            Utente utente = gson.fromJson(messaggio, Utente.class);
            messaggio = buffread.readLine();
            int scelta = gson.fromJson(messaggio, int.class);
            messaggio = buffread.readLine();
            Sondaggio consultazione = gson.fromJson(messaggio, Sondaggio.class);


            Utente u2 = new Utente("a", "b");
            Utente u3 = new Utente("c", "d");
            ArrayList<Utente> v = new ArrayList<>();
            v.add(u2);
            v.add(u3);
            String[] opzioni = {"1", "2", "3", "4", "5"};
            Calendar data = new GregorianCalendar(2024, Calendar.FEBRUARY,10, 0, 0,0);
            Sondaggio c2 = new Sondaggio("Domanda?",v,v, opzioni, data);
            //c2.vota(scelta, u);

            //utilizzabili solo quando ci sara il DB
            /*IUtenteDAO ud = new UtenteDAO();
              ISondaggioDAO cd = new SondaggioDAO();
                utente = ud.selectById(utente);
                IConsultazione c1 = (IConsultazione) cd.selectById(consultazione);
                c1.vota(scelta, user);*/

            Sondaggio sondaggio = new Sondaggio(utente);
            sondaggio.vota(scelta, utente);
            //cd.update(sondaggio);
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
        } catch (ConsultationExpiredException exc1){
            risposta = exc1.getMessage();
            exchange.sendResponseHeaders(418, 0); //I'm a trapot
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (IOException exc){
            exc.printStackTrace();
        }
    }
}