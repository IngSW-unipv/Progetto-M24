package it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.dto.SondaggioDTO;
import it.unipv.ingsfw.opinione360.model.Sondaggio;

import java.util.ArrayList;
import java.util.Iterator;

public class SondaggioMapper {
    public static SondaggioDTO entityToDto(Sondaggio entity){
        SondaggioDTO dto = new SondaggioDTO();
        dto.setId(entity.getId());
        dto.setDescrizione(entity.getQuesito());
        dto.setOpzioni(entity.getOpzioni());
        dto.setVetrina(entity.getVetrina());
        return dto;
    }
     public static ArrayList<SondaggioDTO> entityCollectionToDto(ArrayList<Sondaggio> entity){
        ArrayList<SondaggioDTO> dto = new ArrayList<>();
        int size = entity.size();
        for (Sondaggio s : entity) {
            SondaggioDTO sd = new SondaggioDTO();
            sd.setId(s.getId());
            sd.setDescrizione(s.getQuesito());
            sd.setOpzioni(s.getOpzioni());
            sd.setVetrina(s.getVetrina());
            dto.add(sd);
        }
        return dto;
    }
}
