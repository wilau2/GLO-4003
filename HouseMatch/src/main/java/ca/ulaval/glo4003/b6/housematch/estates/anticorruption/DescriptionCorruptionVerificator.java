package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class DescriptionCorruptionVerificator {

   private EstatesService estateService;

   @Autowired
   public DescriptionCorruptionVerificator(EstatesService estateService) {
      this.estateService = estateService;
   }

   public void editDescription(String address, DescriptionDto descriptionDto) throws InvalidDescriptionFieldException,
         InvalidDescriptionException, InvalidEstateException, CouldNotAccessDataException {
      validateDescriptionCorruption(descriptionDto);
      estateService.editDescription(address, descriptionDto);
   }

   private void validateDescriptionCorruption(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      validateNumberOfBedrooms(descriptionDto.getNumberOfBedRooms());
      validateNumberOfBathrooms(descriptionDto.getNumberOfBathrooms());
      validateNumberOfRooms(descriptionDto.getNumberOfRooms());
      validateNumberOfLevel(descriptionDto.getNumberOfLevel());
      validateYearOfContruction(descriptionDto.getYearOfConstruction());
      validateBuildingDimensions(descriptionDto.getBuildingDimension());
      validateLivingSpaceArea(descriptionDto.getLivingSpaceAreaSquareMeter());
      validateMunicipalAssessment(descriptionDto.getMunicipalAssessment());
      backyardOrientation(descriptionDto.getBackyardOrientation());

   }

   private void backyardOrientation(String backyardOrientation) throws InvalidDescriptionFieldException {
      if (backyardOrientation == null) {
         throw new InvalidDescriptionFieldException("The entered backyard orientation is not valid");
      }
   }

   private void validateMunicipalAssessment(Integer municipalAssessment) throws InvalidDescriptionFieldException {
      if (municipalAssessment == null || municipalAssessment < 0) {
         throw new InvalidDescriptionFieldException("The entered municipal valuation is not valid");
      }
   }

   private void validateLivingSpaceArea(Integer livingSpaceArea) throws InvalidDescriptionFieldException {
      if (livingSpaceArea == null || livingSpaceArea < 0) {
         throw new InvalidDescriptionFieldException("The entered living space area is not valid");
      }
   }

   private void validateBuildingDimensions(String buildingDimension) throws InvalidDescriptionFieldException {
      if (buildingDimension == null) {
         throw new InvalidDescriptionFieldException("The entered building dimension is not valid");
      }
   }

   private void validateYearOfContruction(Integer yearOfConstruction) throws InvalidDescriptionFieldException {
      System.out.println(yearOfConstruction);
      if (yearOfConstruction == null || yearOfConstruction < 0) {
         throw new InvalidDescriptionFieldException("The entered year of construction is not valid");
      }
   }

   private void validateNumberOfLevel(Integer numberOfLevel) throws InvalidDescriptionFieldException {
      if (numberOfLevel == null || numberOfLevel < 0) {
         throw new InvalidDescriptionFieldException("The entered number of level is not valid");
      }
   }

   private void validateNumberOfRooms(Integer numberOfRooms) throws InvalidDescriptionFieldException {
      if (numberOfRooms == null || numberOfRooms < 0) {
         throw new InvalidDescriptionFieldException("The entered number of rooms is not valid");
      }
   }

   private void validateNumberOfBathrooms(Integer numberOfBathrooms) throws InvalidDescriptionFieldException {
      if (numberOfBathrooms == null || numberOfBathrooms < 0) {
         throw new InvalidDescriptionFieldException("The entered number of bathrooms is not valid");
      }
   }

   private void validateNumberOfBedrooms(Integer numberOfBedrooms) throws InvalidDescriptionFieldException {
      if (numberOfBedrooms == null || numberOfBedrooms < 0) {
         throw new InvalidDescriptionFieldException("The entered number of bedrooms is not valid");
      }
   }

}
