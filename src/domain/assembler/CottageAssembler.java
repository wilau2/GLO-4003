package domain.assembler;

import domain.estate.Cottage;
import domain.estate.Estate;
import dto.EstateDto;

public class CottageAssembler implements EstateAssembler
{

    @Override
    public Estate buildEstate(EstateDto estateDto) 
    {
       
        return new Cottage(estateDto.getAddress(), estateDto.getPrice());
    }

}
