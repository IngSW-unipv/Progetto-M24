package it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.model.Utente;

import java.util.ArrayList;

/**
 * Mapper che consente di trasformate un oggetto {@link it.unipv.ingsfw.opinione360.model.Utente} o
 * {@link it.unipv.ingsfw.opinione360.model.Amministratore} in un oggetto {@link it.unipv.ingsfw.opinione360.dto.UtenteDTO}
 */
public class UtenteMapper {
    /**
     * Metodo che consente di mappare un utente singolo
     * @param entity l'oggetto di tipo Utente
     * @return un oggetto {@link it.unipv.ingsfw.opinione360.dto.UtenteDTO}
     */
    public static UtenteDTO entityToDto(Utente entity){
        UtenteDTO dto = new UtenteDTO();
        dto.setType(entity.getClass().getName());
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setIdSocietario(entity.getId_societario());
        return dto;
    }

    /**
     * Metodo che consente di mappare un ArrayList di consultazioni
     * @param entity l'ogetto di tipo ArrayList<Utente>
     * @return un oggetto ArrayList<UtenteDTO>
     */
    public static ArrayList<UtenteDTO> entityCollectionToDto(ArrayList<Utente> entity){
        ArrayList<UtenteDTO> dto = new ArrayList<>();
        for (Utente s : entity) {
            UtenteDTO ud = new UtenteDTO();
            ud.setId(s.getId());
            ud.setUsername(s.getUsername());
            ud.setIdSocietario(s.getId_societario());
            dto.add(ud);
        }
        return dto;
    }
}
