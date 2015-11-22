package ca.ulaval.glo4003.b6.housematch.services.estate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesResearch;

import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EstatesResearchTest {
   
   @Mock
   private EstatesFetcher estateFetcher;
   
   private EstatesResearch estateResearch;

   @Before
   public void setUp(){
      MockitoAnnotations.initMocks(this);
      estateResearch = new EstatesResearch(estateFetcher);
   }
   
   @Test
   public void searchEstatesCallsEstatesFetcherToFetchAllEstates() throws CouldNotAccessDataException{
      //before
      estateResearch.getEstatesByDate();
      //when
      
      //then
      verify(estateFetcher, times(1)).getAllEstates();
   }
}
