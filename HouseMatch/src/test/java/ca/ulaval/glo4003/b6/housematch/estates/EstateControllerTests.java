package ca.ulaval.glo4003.b6.housematch.estates;

import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;

import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class EstateControllerTests {

   @Mock
   private EstateFactory estateFactory;

   @Mock
   private EstateDto estateDto;

   @Mock
   private Estate estate;

   @Mock
   private EstateRepository estateRepository;

   @InjectMocks
   private EstateController estateController;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);

   }

   @Test
   public void addEstateCallsTheEstateFactory() {
      // given no changes

      // when
      estateController.addEstate(estateDto);

      // then
      verify(estateFactory).createEstate(estateDto);
   }

   @Test
   public void saveEstateCallsTheRepositoryInterfaceSaveEstateImplementationFunction() {
      // given
      given(estateFactory.createEstate(estateDto)).willReturn(estate);

      // when
      estateController.saveEstate(estate);

      // then
      verify(estateRepository).saveEstate(estate);

   }

}
