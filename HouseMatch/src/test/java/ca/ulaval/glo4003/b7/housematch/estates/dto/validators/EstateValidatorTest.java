package ca.ulaval.glo4003.b7.housematch.estates.dto.validators;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b7.housematch.estates.exceptions.InvalidEstateException;

public class EstateValidatorTest {

    private EstateValidator estateValidator;
    
    private String INVALID_TYPE_ESTATE = "invalid";
    private String VALID_TYPE_ESTATE = "CONDO";
    
    private String VALID_ADDRESS = "2-128 rue untel, Quebec";
    private String INVALID_ADDRESS = "";
    
    private Integer VALID_PRICE = 125000; 
    private Integer INVALID_PRICE = 0;
  
    
    
    @Before
    public void setUp(){

    	estateValidator = new EstateValidator();
    	
    }
    
    @Test(expected = InvalidEstateException.class)
    public void validatingEstateWithNoTypeShouldThrowException() throws InvalidEstateException
    {
    	
    //Given
        
    //When
    estateValidator.validate(INVALID_TYPE_ESTATE, VALID_ADDRESS, VALID_PRICE);
    
    //Then handle by expectedException
    }
    
    @Test(expected = InvalidEstateException.class)
    public void validatingEstateWithNoAdressShouldThrowException() throws InvalidEstateException
    {
        //Given
        
        //When
        estateValidator.validate(VALID_TYPE_ESTATE, INVALID_ADDRESS, VALID_PRICE);
        //Then handle expectedException
    }
    
    @Test(expected = InvalidEstateException.class)
    public void validatingEstateWithNoIntegerShouldThrowException() throws InvalidEstateException
    {
        //Given
        
        //When
        estateValidator.validate(VALID_TYPE_ESTATE, VALID_ADDRESS, INVALID_PRICE);
        //Then handle by expectedException
    }
            
            
}
