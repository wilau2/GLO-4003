package ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;

public class DescriptionElementAssembler {

   private static final String NUMBER_OF_BEDROOMS = "number_of_bedrooms";
   private static final String NUMBER_OF_BATHROOMS = "number_of_bathrooms";
   private static final String NUMBER_OF_ROOMS = "number_of_rooms";
   private static final String NUMBER_OF_LEVELS = "number_of_levels";
   private static final String YEAR_OF_CONSTRUCTION = "year_of_construction";
   private static final String BUILDING_DIMENSIONS = "building_dimensions";
   private static final String LIVING_SPACE = "living_space";
   private static final String MUNICIPAL_VALUATION = "municipal_valuation";
   private static final String BACKYARD_FACES = "backyard_faces";

   public HashMap<String, String> convertToAttributes(Description description) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put(NUMBER_OF_BEDROOMS, description.getNumberOfBedRooms().toString());
      attributes.put(NUMBER_OF_BATHROOMS, description.getNumberOfBathrooms().toString());
      attributes.put(NUMBER_OF_ROOMS, description.getNumberOfRooms().toString());
      attributes.put(NUMBER_OF_LEVELS, description.getNumberOfLevel().toString());
      attributes.put(YEAR_OF_CONSTRUCTION, description.getYearsOfConstruction().toString());
      attributes.put(BUILDING_DIMENSIONS, description.getDimensionsBuilding());
      attributes.put(LIVING_SPACE, description.getLivingSpaceAreaSquareMeter().toString());
      attributes.put(MUNICIPAL_VALUATION, description.getMunicipalValuation().toString());
      attributes.put(BACKYARD_FACES, description.getBackyardFaces());

      return attributes;
   }

}
