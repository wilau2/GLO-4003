package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatesResearch {
   
   private EstatesFetcher estatesFetcher;
   
   public EstatesResearch(EstatesFetcher estatesFetcher){
      this.estatesFetcher = estatesFetcher;
   }

   public void getEstatesByDate() throws CouldNotAccessDataException {
      
      List<EstateDto> estateList = estatesFetcher.getAllEstates();
   }

}
