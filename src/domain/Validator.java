package domain;

import java.util.Arrays;
import java.util.List;

import customException.AddressFormatException;
import customException.EstateTypeException;
import customException.PriceException;
import dto.EstateDto;

public class Validator 
{
    private List<String> estateList = Arrays.asList("CONDO", "APPARTMENT", "SINGLE_FAMILY", "MULTIPLEX", "LOT", "FARM", "COTTAGE", "COMMERCIAL");
    public void validateEstateDto(EstateDto estateDto) throws EstateTypeException, AddressFormatException, PriceException
    {
      validateType(estateDto.getType());
      validateAddress(estateDto.getAddress());
      validatePrice(estateDto.getPrice());
    }
    
    private void validateType(String type) throws EstateTypeException 
    {
        if(!estateList.contains(type))
        {
            throw new EstateTypeException();
        }
    }
    
    private void validateAddress(String address) throws AddressFormatException
    {
        if(address == "")
        {
            throw new AddressFormatException();
        }
    }
    
    private void validatePrice(Integer price) throws PriceException
    {
        if(!(price > 0))
        {
            throw new PriceException();
        }
    }

}
