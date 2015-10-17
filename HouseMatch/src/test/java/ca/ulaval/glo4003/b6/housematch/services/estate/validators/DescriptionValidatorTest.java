package ca.ulaval.glo4003.b6.housematch.services.estate.validators;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;

public class DescriptionValidatorTest {

   @Mock
   private DescriptionDto descriptionDto;

   @InjectMocks
   private DescriptionValidator descriptionValidator;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);
      configureValidDescriptionDto();
   }

   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithNegativeNumberOfBathRoomsThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(-1);
      // when
      descriptionValidator.validate(descriptionDto);
      // then InvalidDescriptionException is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithZeroNumberOfBathRoomsThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(0);
      // when
      descriptionValidator.validate(descriptionDto);
      // then InvalidDescriptionException is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithNegativeNumberOfBedRoomsThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(-1);
      // when
      descriptionValidator.validate(descriptionDto);
      // then
      // exception is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithZeroNumberOfRoomsThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(0);
      // when
      descriptionValidator.validate(descriptionDto);
      // then
      // exception is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void whenNumberOfRoomsIsUnderOneThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getNumberOfRooms()).thenReturn(0);
      // when
      descriptionValidator.validate(descriptionDto);
      // then
      // exception is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void whenNumberOfLevelsIsUnderOneThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getNumberOfLevel()).thenReturn(0);
      // when
      descriptionValidator.validate(descriptionDto);
      // then InvalidDescriptionException is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void whenYearOfConstructionIsUnderOneThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getYearOfConstruction()).thenReturn(0);
      // when
      descriptionValidator.validate(descriptionDto);
      // then InvalidDescriptionException is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void whenLivingSpaceUnderOneThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(0);
      // when
      descriptionValidator.validate(descriptionDto);
      // then InvalidDescriptionException is catched
   }

   @Test(expected = InvalidDescriptionException.class)
   public void whenMunicipalValueUnderOneThrowsException() throws InvalidDescriptionException {
      // given
      when(descriptionDto.getMunicipalAssessment()).thenReturn(0);
      // when
      descriptionValidator.validate(descriptionDto);
      // then InvalidDescriptionException is catched
   }

   @Test
   public void whenValidatingCorrectDtoNoExceptionThrown() throws InvalidDescriptionException {
      // given no changes

      // when
      descriptionValidator.validate(descriptionDto);

      // then no exceptions are thrown
   }

   private void configureValidDescriptionDto() {
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(2);
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(2);
      when(descriptionDto.getNumberOfRooms()).thenReturn(2);
      when(descriptionDto.getNumberOfLevel()).thenReturn(2);
      when(descriptionDto.getYearOfConstruction()).thenReturn(2000);
      when(descriptionDto.getBuildingDimension()).thenReturn("valid dimension");
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(150);
      when(descriptionDto.getMunicipalAssessment()).thenReturn(160000);
      when(descriptionDto.getBackyardOrientation()).thenReturn("valid backyardFaces");
   }

}
