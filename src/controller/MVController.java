package controller;

import antiCorruption.EstateAntiCorruption;
import customException.AddressFormatException;
import customException.ArgumentNullDtoException;
import customException.EstateTypeException;
import customException.PriceException;
import dto.EstateDto;

public class MVController 
{
    private EstateAntiCorruption estateAntiCorruption;
    private EstateDto userDto;
    
    public MVController()
    {
        this.estateAntiCorruption = new EstateAntiCorruption();
    }
    
    public void addEstate() throws ArgumentNullDtoException, EstateTypeException, AddressFormatException, PriceException
    {
        estateAntiCorruption.addEstate(userDto);
        
        // format response
        
        // return page
    }

}
