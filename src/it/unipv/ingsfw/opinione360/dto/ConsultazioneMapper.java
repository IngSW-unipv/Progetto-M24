package it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.model.IConsultazione;

import java.util.ArrayList;

/**
 * Mapper che consente di trasformate un oggetto {@link it.unipv.ingsfw.opinione360.model.Sondaggio} in
 * un oggetto {@link it.unipv.ingsfw.opinione360.dto.ConsultazioneDTO}
 */
public class ConsultazioneMapper {
    /**
     * Metodo che consente di mappare una consultazione singola
     * @param entity l'oggetto di tipo IConsultazione
     * @return un oggetto {@link it.unipv.ingsfw.opinione360.dto.ConsultazioneDTO}
     */
    public static ConsultazioneDTO entityToDto(IConsultazione entity){
        ConsultazioneDTO dto = new ConsultazioneDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getClass().getName());
        dto.setDescrizione(entity.getQuesito());
        dto.setListaOpzioni(entity.getListaOpzioni());
        return dto;
    }

    /**
     * Metodo che consente di mappare un ArrayList di consultazioni
     * @param entity l'ogetto di tipo ArrayList<IConsultazione>
     * @return un oggetto ArrayList<ConsultazioneDTO>
     */
     public static ArrayList<ConsultazioneDTO> entityCollectionToDto(ArrayList<IConsultazione> entity){
        ArrayList<ConsultazioneDTO> dto = new ArrayList<>();
        for (IConsultazione s : entity) {
            ConsultazioneDTO sd = new ConsultazioneDTO();
            sd.setType(entity.getClass().getName());
            sd.setId(s.getId());
            sd.setDescrizione(s.getQuesito());
            dto.add(sd);
        }
        return dto;
    }
}
