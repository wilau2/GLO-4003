package ca.ulaval.glo4003.b6.housematch.estates.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesFetcherFactoryTest {

   private EstatesFetcherFactory estatesFetcherFactory;

   @Mock
   private EstateAssemblerFactory estateAssembleFactory;

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estatesFetcherFactory = new EstatesFetcherFactory();
   }

   @Test
   public void whenAskingForANewInstanceShouldReturnWantedObject() {
      // Given no changes

      // When
      Object returnedObject = estatesFetcherFactory.newInstance(estateAssembleFactory, estateRepositoryFactory);

      // Then
      assertTrue(returnedObject instanceof EstatesFetcher);
   }
}
