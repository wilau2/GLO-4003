package ca.ulaval.glo4003.b7.housematch.estates.dto.validators;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b7.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b7.housematch.estates.exceptions.InvalidEstateException;

public class EstateValidatorTest {

    private EstateValidator estateValidator;
    private EstateDto estateDto;
    
    
    @Before
    public void setUp(){
    	estateDto = new EstateDto();
    	estateValidator = new EstateValidator();
    	
    }
    
    @Test(expected = InvalidEstateException.class)
    public void validatingEstateDtoWithNoTypeShouldThrowException() throws InvalidEstateException{
    	//Given
    
    //When
    estateValidator.validate(estateDto);
    
    //Then handle by expectedException
    }
}
