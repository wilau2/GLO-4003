package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;

public class DescriptionValidatorTest {
   
   @Mock
   private DescriptionDto descriptionDto;
   
   @InjectMocks
   private DescriptionValidator descriptionValidator;
   
   @Before
   public void setUp(){
      MockitoAnnotations.initMocks(this);
      configureValidDescriptionDto();
   }
   
   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithNegativeNumberOfBathRoomsThrowsException() throws InvalidDescriptionException {
      //given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(-1);
      //when
      descriptionValidator.validate(descriptionDto);
      //then
      //exception is catched
   }
   
   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithZeroNumberOfBathRoomsThrowsException() throws InvalidDescriptionException {
      //given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(0);
      //when
      descriptionValidator.validate(descriptionDto);
      //then
      //exception is catched
   }
   
   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithNegativeNumberOfBedRoomsThrowsException() throws InvalidDescriptionException {
      //given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(-1);
      //when
      descriptionValidator.validate(descriptionDto);
      //then
      //exception is catched
   }
   
   @Test(expected = InvalidDescriptionException.class)
   public void descriptionWithZeroNumberOfRoomsThrowsException() throws InvalidDescriptionException {
      //given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(0);
      //when
      descriptionValidator.validate(descriptionDto);
      //then
      //exception is catched
   }
   
   @Test(expected = InvalidDescriptionException.class)
   public void whenNumberOfRoomsIsUnderOneThrowsException() throws InvalidDescriptionException {
      //given
      when(descriptionDto.getNumberOfRooms()).thenReturn(0);
      //when
      descriptionValidator.validate(descriptionDto);
      //then
      //exception is catched
   }
   
   private void configureValidDescriptionDto(){
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(2);
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(2);
      when(descriptionDto.getNumberOfRooms()).thenReturn(2);
      when(descriptionDto.getNumberOfLevel()).thenReturn(2);
      when(descriptionDto.getYearsOfConstruction()).thenReturn(2000);
      when(descriptionDto.getDimensionsBuilding()).thenReturn("valid dimension");
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(150);
      when(descriptionDto.getMunicipalValuation()).thenReturn(160000);
      when(descriptionDto.getBackyardFaces()).thenReturn("valid backyardFaces");
   }
   
   
}
