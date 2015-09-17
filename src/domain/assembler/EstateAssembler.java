package domain.assembler;

import domain.estate.Estate;
import dto.EstateDto;

public interface EstateAssembler 
{
    
    public Estate buildEstate(EstateDto estateDto);

}
