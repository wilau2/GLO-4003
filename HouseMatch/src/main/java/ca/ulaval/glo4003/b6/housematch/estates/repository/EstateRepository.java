package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public interface EstateRepository {

   void addEstate(Estate estate) throws CouldNotAccessDataException;

   void editEstate(Estate estate);

   List<Estate> getAllEstates() throws CouldNotAccessDataException;

   List<Estate> getEstateFromSeller(String sellerName) throws SellerNotFoundException, CouldNotAccessDataException;

}
