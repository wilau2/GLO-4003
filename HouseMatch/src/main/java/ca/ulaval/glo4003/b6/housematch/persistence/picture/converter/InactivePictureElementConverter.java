package ca.ulaval.glo4003.b6.housematch.persistence.picture.converter;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.dto.InactivePictureDto;

public class InactivePictureElementConverter {

   private static final String UUID = "uid";

   private static final String ADDRESS = "address";

   private static final String NAME = "name";

   private static final String ACTIVE = "active";

   public InactivePictureElementConverter() {

   }

   public InactivePictureDto convertToDto(Element element) {
      InactivePictureDto inactivePictureDto = new InactivePictureDto(element.elementText(UUID),
            element.elementText(ADDRESS), element.elementText(NAME), element.elementText(ACTIVE));
      return inactivePictureDto;
   }

}
