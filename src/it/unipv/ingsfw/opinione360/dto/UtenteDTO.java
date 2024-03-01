package it.unipv.ingsfw.opinione360.dto;

import java.util.UUID;

/**
 * POJO per l'invio di utenti al client
 */
public class UtenteDTO {
    private String type;
    private UUID id;
    private String username;
    private String idSocietario;

    public String getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getIdSocietario() {
        return idSocietario;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdSocietario(String idSocietario) {
        this.idSocietario = idSocietario;
    }
}
