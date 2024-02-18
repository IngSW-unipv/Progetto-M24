package it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.model.Utente;

import java.util.ArrayList;

public class UtenteMapper {
    public static UtenteDTO entityToDto(Utente entity){
        UtenteDTO dto = new UtenteDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        return dto;
    }
     public static ArrayList<UtenteDTO> entityCollectionToDto(ArrayList<Utente> entity){
        ArrayList<UtenteDTO> dto = new ArrayList<>();
        for (Utente s : entity) {
            UtenteDTO ud = new UtenteDTO();
            ud.setId(s.getId());
            ud.setUsername(s.getUsername());
            ud.setEmail(s.getEmail());
            dto.add(ud);
        }
        return dto;
    }
}
