package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public interface EstateRepository {

   void addEstate(Estate estate) throws CouldNotAccessDataException;

   List<Estate> getAllEstates() throws CouldNotAccessDataException;

   List<Estate> getEstateFromSeller(String sellerName) throws SellerNotFoundException, CouldNotAccessDataException;

   Estate getEstateByAddress(String address) throws EstateNotFoundException, CouldNotAccessDataException;

   void updateEstate(Estate estate) throws CouldNotAccessDataException;

}
