package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;

public interface EstateRepository {

   void addEstate(Estate estate);

   void editEstate(Estate estate);

   List<Estate> getAllEstates();

   List<Estate> getEstateFromSeller(String sellerName) throws SellerNotFoundException;

}
