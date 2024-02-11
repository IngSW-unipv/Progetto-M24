package it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.model.Vetrina;

import java.util.UUID;

public class SondaggioDTO {
    private UUID id;
    private String descrizione;
    private String [] opzioni;
    private Vetrina vetrina;

    public UUID getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String [] getOpzioni() {
        return opzioni;
    }

    public Vetrina getVetrina() {
        return vetrina;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setOpzioni(String [] opzioni) {
        this.opzioni = opzioni;
    }

    public void setVetrina(Vetrina vetrina) {
        this.vetrina = vetrina;
    }

}
