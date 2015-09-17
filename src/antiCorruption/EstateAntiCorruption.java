package antiCorruption;

import customException.AddressFormatException;
import customException.ArgumentNullDtoException;
import customException.EstateTypeException;
import customException.PriceException;
import dto.EstateDto;
import service.EstateService;

public class EstateAntiCorruption 
{
    private EstateService estateService;

    public void addEstate(EstateDto userDto) throws ArgumentNullDtoException, EstateTypeException, AddressFormatException, PriceException 
    {
        validateUserDto(userDto);
        estateService.addEstate(userDto);  
    }
    
    private void validateUserDto(EstateDto userDto) throws ArgumentNullDtoException
    {
        if(userDto.getType() == null || userDto.getAddress() == null || !(userDto.getPrice() > 0))
        {
            throw new ArgumentNullDtoException();
        }
    }
}
