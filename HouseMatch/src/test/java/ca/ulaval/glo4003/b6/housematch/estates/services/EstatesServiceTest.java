package ca.ulaval.glo4003.b6.housematch.estates.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesServiceTest {

   private static final String VALID_TYPE_ESTATE = "CONDO";

   private static final String INVALID_TYPE_ESTATE = "invalid";

   private static final String VALID_ADDRESS = "2-128 rue untel, Quebec";

   private static final Integer VALID_PRICE = 125000;

   @Mock
   private EstateValidatorFactory estateValidatorFactory;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstateRepository estateRepository;

   @Mock
   private Estate estate;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstateAssembler estateAssembler;

   @Mock
   private EstateValidator estateValidator;

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   // @InjectMocks
   private EstatesService estatesService;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);

      when(estateValidatorFactory.getValidator()).thenReturn(estateValidator);
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);
      when(estateRepositoryFactory.newInstance(estateAssemblerFactory, estatePersistenceDtoFactory))
            .thenReturn(estateRepository);

      estatesService = new EstatesService(estateValidatorFactory, estateAssemblerFactory, estateRepositoryFactory,
            estatePersistenceDtoFactory);
   }

   @Test
   public void addingAnEstateWhenEstateIsValidShouldCallAddEstateAtRepository() throws InvalidEstateException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateRepository, times(1)).addEstate(estate);
   }

   @Test
   public void whenAddingAnEstateShouldCallGetAssemblerFromAssemblerFactory() throws InvalidEstateException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
   }

   @Test(expected = InvalidEstateException.class)
   public void addingEstateWhenValidatingInvalidEstateShouldThrowException() throws InvalidEstateException {
      // Given
      doThrow(new InvalidEstateException()).when(estateValidator).validate(estateDto);

      // When
      estatesService.addEstate(estateDto);

      // Then InvalidEstateException is thrown
   }

   @Test
   public void whenAskedAllEstatesShouldCallAssembleEstate() {
      // given
      List<Estate> dumbEstates = new ArrayList<Estate>();
      dumbEstates.add(estate);
      when(estateRepository.getAllEstates()).thenReturn(dumbEstates);

      // when
      estatesService.getAllEstates();

      // then
      verify(estateRepository, times(1)).getAllEstates();
   }

   @Test
   public void whenAskedAllEstatesShouldCallEstateAssembleDtoWithEstate() {
      // given
      List<Estate> dumbEstateDtoList = new ArrayList<Estate>();
      dumbEstateDtoList.add(estate);
      when(estateRepository.getAllEstates()).thenReturn(dumbEstateDtoList);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);

      // when
      estatesService.getAllEstates();

      // then
      verify(estateAssembler, times(1)).assembleEstateDto(estate);
   }

   @Test
   public void whenEditingEstateShouldCallEstateRepositoryEditEstate() throws InvalidEstateException {
      // given no changes

      // when
      estatesService.editEstate(estateDto);

      // then
      verify(estateRepository, times(1)).editEstate(estate);
   }
}
