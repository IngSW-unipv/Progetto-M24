package it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.model.AreaOpzione;

import java.util.ArrayList;
import java.util.UUID;

/**
 * POJO per l'invio di consultazioni al client
 */

public class ConsultazioneDTO {
    private String type;
    private UUID id;
    private String descrizione;
    private ArrayList<AreaOpzione> listaOpzioni;

    public String getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public ArrayList<AreaOpzione> getListaOpzioni() {
        return listaOpzioni;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setListaOpzioni(ArrayList<AreaOpzione> listaOpzioni) {
        this.listaOpzioni = listaOpzioni;
    }
}
