package ca.ulaval.glo4003.b7.housematch.assembler;

import ca.ulaval.glo4003.b7.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b7.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b7.housematch.estates.exceptions.InvalidEstateException;

public class EstateAssembler {
    
    private EstateValidator estateValidator;
    
    public EstateAssembler(EstateValidator estateValidator) 
    {
        this.estateValidator = estateValidator;
    }


    public EstateDto createDTO(String type, String address, Integer price) throws InvalidEstateException
    {
        
        estateValidator.validate(type, address, price);
        
        return new EstateDto(type, address, price);
    }

}
