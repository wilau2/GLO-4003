package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstateModifiedDateObserverTest {

   private static final String ADDRESS = "ADDRESS";

   private EstateModifiedDateObserver estateModifiedDateObserver;

   @Mock
   private EstateRepository estateRepository;

   @Mock
   private Estate estate;

   @Before
   public void setup() throws EstateNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);

      configureEstateRepository();
      estateModifiedDateObserver = new EstateModifiedDateObserver(estateRepository);
   }

   private void configureEstateRepository() throws EstateNotFoundException, CouldNotAccessDataException {
      when(estateRepository.getEstateByAddress(ADDRESS)).thenReturn(estate);
   }

   @Test
   public void whenNotifiedOfAnChangeOfEstateShouldFetchEstateFromRepository()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateModifiedDateObserver.notify(ADDRESS);

      // Then
      verify(estateRepository, times(1)).getEstateByAddress(ADDRESS);
   }

   @Test
   public void whenNotifyingEstateChangedShouldUpdateTheDateOfLastModification()
         throws CouldNotAccessDataException, EstateNotFoundException {
      // Given no changes

      // When
      estateModifiedDateObserver.notify(ADDRESS);

      // Then
      verify(estate, times(1)).updateModifiedDate();
   }

   @Test
   public void whenNotifyingEstateChangedShouldSaveNewlyUpdatedEstate()
         throws CouldNotAccessDataException, EstateNotFoundException {
      // Given no changes

      // When
      estateModifiedDateObserver.notify(ADDRESS);

      // Then
      verify(estateRepository, times(1)).updateEstate(estate);
   }

}
