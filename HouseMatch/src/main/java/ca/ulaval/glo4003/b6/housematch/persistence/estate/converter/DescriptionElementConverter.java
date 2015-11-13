package ca.ulaval.glo4003.b6.housematch.persistence.estate.converter;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;

public class DescriptionElementConverter {

   private static final String NUMBER_OF_BEDROOMS = "numberOfBedRooms";

   private static final String NUMBER_OF_BATHROOMS = "numberOfBathrooms";

   private static final String NUMBER_OF_ROOMS = "numberOfRooms";

   private static final String NUMBER_OF_LEVELS = "numberOfLevel";

   private static final String YEAR_OF_CONSTRUCTION = "yearsOfConstruction";

   private static final String BUILDING_DIMENSIONS = "dimensionsBuilding";

   private static final String LIVING_SPACE = "livingSpaceAreaSquareMeter";

   private static final String MUNICIPAL_VALUATION = "municipalValuation";

   private static final String BACKYARD_FACES = "backyardFaces";

   public HashMap<String, String> convertToAttributes(Description description) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put(NUMBER_OF_BEDROOMS, description.getNumberOfBedRooms().toString());
      attributes.put(NUMBER_OF_BATHROOMS, description.getNumberOfBathrooms().toString());
      attributes.put(NUMBER_OF_ROOMS, description.getNumberOfRooms().toString());
      attributes.put(NUMBER_OF_LEVELS, description.getNumberOfLevel().toString());
      attributes.put(YEAR_OF_CONSTRUCTION, description.getYearOfConstruction().toString());
      attributes.put(BUILDING_DIMENSIONS, description.getBuildingDimensions());
      attributes.put(LIVING_SPACE, description.getLivingSpaceAreaSquareMeter().toString());
      attributes.put(MUNICIPAL_VALUATION, description.getMunicipalAssessment().toString());
      attributes.put(BACKYARD_FACES, description.getBackyardOrientation());

      return attributes;
   }

   public DescriptionDto convertAttributesToDto(HashMap<String, String> attributes) {
      DescriptionDto descriptionDto = new DescriptionDto();

      Integer numberOfBedRooms = tryParseInt(attributes.get(NUMBER_OF_BEDROOMS));
      Integer numberOfBathrooms = tryParseInt(attributes.get(NUMBER_OF_BATHROOMS));
      Integer numberOfRooms = tryParseInt(attributes.get(NUMBER_OF_ROOMS));
      Integer numberOfLevel = tryParseInt(attributes.get(NUMBER_OF_LEVELS));
      Integer yearsOfConstruction = tryParseInt(attributes.get(YEAR_OF_CONSTRUCTION));
      String dimensionsBuilding = attributes.get(BUILDING_DIMENSIONS);
      Integer livingSpaceAreaSquareMeter = tryParseInt(attributes.get(LIVING_SPACE));
      Integer municipalValuation = tryParseInt(attributes.get(MUNICIPAL_VALUATION));
      String backyardFaces = attributes.get(BACKYARD_FACES);

      descriptionDto.setNumberOfBedRooms(numberOfBedRooms);
      descriptionDto.setNumberOfBathrooms(numberOfBathrooms);
      descriptionDto.setNumberOfRooms(numberOfRooms);
      descriptionDto.setNumberOfLevel(numberOfLevel);
      descriptionDto.setYearsOfConstruction(yearsOfConstruction);
      descriptionDto.setBuildingDimension(dimensionsBuilding);
      descriptionDto.setLivingSpaceAreaSquareMeter(livingSpaceAreaSquareMeter);
      descriptionDto.setMunicipalAssessment(municipalValuation);
      descriptionDto.setBackyardOrientation(backyardFaces);

      return descriptionDto;
   }

   public Integer tryParseInt(String str) {
      Integer retVal = 0;
      try {
         retVal = Integer.parseInt(str);
      } finally {
         return retVal;
      }

   }
}
