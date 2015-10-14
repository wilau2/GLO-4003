package ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;

public class DescriptionElementAssembler {

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
      attributes.put(BACKYARD_FACES, description.getBackyardFaces());

      return attributes;
   }
   
   public DescriptionDto convertAttributesToDto(HashMap<String, String> attributes){
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
         descriptionDto.setDimensionsBuilding(dimensionsBuilding);
         descriptionDto.setLivingSpaceAreaSquareMeter(livingSpaceAreaSquareMeter);
         descriptionDto.setMunicipalValuation(municipalValuation);
         descriptionDto.setBackyardFaces(backyardFaces);
      
      return descriptionDto;
   }
   
   
   public Integer tryParseInt(String str) {
      Integer retVal;
      try {
        retVal = Integer.parseInt(str);
      } catch (NumberFormatException nfe) {
        retVal = 0; 
      }
      return retVal;
    }
}
