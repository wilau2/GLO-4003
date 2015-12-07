package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.DescriptionCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.ChangeVerificator;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateAlreadyBoughtException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateEditDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.estate.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.DescriptionValidator;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.EstateValidator;

public class EstatesServiceTest {

   private static final String ADDRESS = "address";

   private static final Integer PRICE = 1000;

   private static final String TYPE = "PRICE";

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstateEditDto estateEditDto;

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
   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   @Mock
   private DescriptionAssembler descriptionAssembler;

   @Mock
   private DescriptionDto descriptionDto;

   @Mock
   private Description description;

   @Mock
   private DescriptionValidator descriptionValidator;

   @Mock
   private AddressDto addressDto;

   @Mock
   private ChangeVerificator changeVerificator;

   @Mock
   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Mock
   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;

   private EstatesService estatesService;

   @Before
   public void setUp() throws EstateNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);

      configureDescriptionTests();

      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);

      when(estateRepository.getEstateByAddress(ADDRESS)).thenReturn(estate);
      when(estateDto.getAddress()).thenReturn(addressDto);
      when(estateDto.getDescriptionDto()).thenReturn(descriptionDto);
      when(estateEditDto.getType()).thenReturn(TYPE);
      when(estateEditDto.getPrice()).thenReturn(PRICE);

      estatesService = new EstatesService(estateCorruptionVerificator, descriptionCorruptionVerificator,
            estateValidator, estateAssemblerFactory, estateRepository, changeVerificator);
   }

   @Test
   public void addingAnEstateWhenEstateIsValidShouldCallAddEstateAtRepository()
         throws InvalidEstateException, CouldNotAccessDataException, InvalidEstateFieldException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateRepository, times(1)).addEstate(estate);
   }

   @Test
   public void whenAddingAnEstateShouldCallGetAssemblerFromAssemblerFactory()
         throws InvalidEstateException, CouldNotAccessDataException, InvalidEstateFieldException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
   }

   @Test
   public void editingAnEstateWhenEstateIsValidShouldCallUpdateEstateAtRepository()
         throws CouldNotAccessDataException, EstateNotFoundException, InvalidEstateFieldException {
      // Given no changes

      // When
      estatesService.editEstate(ADDRESS, estateEditDto);

      // Then
      verify(estateRepository, times(1)).updateEstate(estate);
   }

   @Test(expected = InvalidEstateException.class)
   public void addingEstateWhenValidatingInvalidEstateShouldThrowException()
         throws InvalidEstateException, CouldNotAccessDataException, InvalidEstateFieldException {
      // Given
      doThrow(new InvalidEstateException("")).when(estateValidator).validate(estateDto);

      // When
      estatesService.addEstate(estateDto);

      // Then InvalidEstateException is thrown
   }

   @Test
   public void whenAddingDescriptionShouldAssembleTheDescription()
         throws InvalidDescriptionException, InvalidEstateException, CouldNotAccessDataException,
         EstateNotFoundException, InvalidDescriptionFieldException {
      // given no changes

      // when
      estatesService.editDescription(ADDRESS, descriptionDto);
      // then
      verify(estateAssembler).assembleDescription(descriptionDto);
   }

   @Test
   public void whenEditingDescriptionShouldCallUpdateDescriptionOnEstate()
         throws CouldNotAccessDataException, EstateNotFoundException, InvalidDescriptionFieldException {
      // Given
      when(estateAssembler.assembleDescription(descriptionDto)).thenReturn(description);

      // When
      estatesService.editDescription(ADDRESS, descriptionDto);

      // Then
      verify(estate, times(1)).editDescription(description, changeVerificator);
   }

   @Test
   public void wehnEditingDescriptionShouldCallDescriptionCorruptionVerificator()
         throws CouldNotAccessDataException, EstateNotFoundException, InvalidDescriptionFieldException {
      // Given no changes

      // When
      estatesService.editDescription(ADDRESS, descriptionDto);

      // Then
      verify(descriptionCorruptionVerificator, times(1)).validateDescriptionCorruption(descriptionDto);
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void editingDescriptionWhenCorruptionVerificatorThrowInvalidFieldExceptionShouldThrowInvalidFieldException()
         throws CouldNotAccessDataException, EstateNotFoundException, InvalidDescriptionFieldException {
      // Given
      doThrow(new InvalidDescriptionFieldException("")).when(descriptionCorruptionVerificator)
            .validateDescriptionCorruption(descriptionDto);

      // When
      estatesService.editDescription(ADDRESS, descriptionDto);

      // Then an invalidDescriptionException is thrown
   }

   private void configureDescriptionTests() {
      when(descriptionAssembler.assembleDescription(descriptionDto)).thenReturn(description);
   }

   @Test(expected = EstateNotFoundException.class)
   public void buyingAnEstateWhenEstateDoesNotExistsShouldThrowException()
         throws EstateNotFoundException, CouldNotAccessDataException, EstateAlreadyBoughtException {
      // Given
      doThrow(new EstateNotFoundException("")).when(estateRepository).getEstateByAddress(ADDRESS);

      // When
      estatesService.buyEstate(ADDRESS);

      // Then an estate not found exception is thrown
   }

   @Test
   public void buyingAnEstateWhenEstateWasBoughtShouldCallUpdateMethodOfRepository()
         throws CouldNotAccessDataException, EstateNotFoundException, EstateAlreadyBoughtException {
      // Given

      // When
      estatesService.buyEstate(ADDRESS);

      // Then
      verify(estateRepository, times(1)).updateEstate(estate);

   }

   @Test(expected = EstateAlreadyBoughtException.class)
   public void buyingAnEstateWhenEstateWasAlreadyBoughtShouldThrowException()
         throws EstateAlreadyBoughtException, EstateNotFoundException, CouldNotAccessDataException {
      // Given
      doThrow(new EstateAlreadyBoughtException("")).when(estate).buy();

      // When
      estatesService.buyEstate(ADDRESS);

      // Then an Estate already bought exception is thrown
   }

   @Test
   public void whenBuyingAnEstateShouldFetchEstateFromRepository()
         throws EstateNotFoundException, CouldNotAccessDataException, EstateAlreadyBoughtException {
      // Given no changes

      // When
      estatesService.buyEstate(ADDRESS);

      // Then
      verify(estateRepository, times(1)).getEstateByAddress(ADDRESS);
   }

   @Test
   public void whenBuyingAnEstateShouldCallBuyMethodOnEstateWithCorrespondingAddress()
         throws EstateAlreadyBoughtException, EstateNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estatesService.buyEstate(ADDRESS);

      // Then
      verify(estate, times(1)).buy();
   }

   @Test
   public void whenEditingEstateShouldCallEstateAntiCorruption()
         throws InvalidEstateFieldException, EstateNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesService.editEstate(ADDRESS, estateEditDto);

      // Then
      verify(estateCorruptionVerificator, times(1)).validateEstateEditCorruption(estateEditDto);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void editingEstateWhenCorruptionVerificatorThrowExceptionShouldThrowException()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidEstateFieldException {
      // Given
      doThrow(new InvalidEstateFieldException("")).when(estateCorruptionVerificator)
            .validateEstateEditCorruption(estateEditDto);

      // When
      estatesService.editEstate(ADDRESS, estateEditDto);

      // Then an invalid estate field exception is thrown
   }

   @Test
   public void whenAddingEstateShouldCallEstateAntiCorruptionVerificator()
         throws InvalidEstateException, CouldNotAccessDataException, InvalidEstateFieldException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateCorruptionVerificator, times(1)).validateEstateCorruption(estateDto);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateWhenCorruptionVerificatorThrowInvalidFieldExceptionShouldThrowException()
         throws InvalidEstateException, CouldNotAccessDataException, InvalidEstateFieldException {
      // Given
      doThrow(new InvalidEstateFieldException("")).when(estateCorruptionVerificator)
            .validateEstateCorruption(estateDto);

      // When
      estatesService.addEstate(estateDto);

      // Then an invalid estate field exception is thrown
   }
}
