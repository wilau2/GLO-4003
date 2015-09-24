package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

public class XMLEstateRepository implements EstateRepository {

   private static final String ESTATE = "estate";

   private XMLFileEditor xmlFileEditor;

   private String XML_FILE_PATH = "persistence/estates.xml";

   @Override
   public List<EstateDto> getAllEstatesDto() {
      List<EstateDto> estateDtoList = new ArrayList<>();
      try {
         Document estateDocument = xmlFileEditor.readXMLFile(XML_FILE_PATH);
         Collection<Element> elementList = xmlFileEditor.getAllElementsFromDocument(estateDocument, ESTATE);
         estateDtoList = getDtoListFromElements(elementList);

      } catch (DocumentException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return estateDtoList;
   }

   private List<EstateDto> getDtoListFromElements(Collection<Element> elementList) {
      List<EstateDto> estateDtoList = new ArrayList<EstateDto>();

      for (Element element : elementList) {
         estateDtoList.add(convertElementToDto(element));
      }

      return estateDtoList;
   }

   private EstateDto convertElementToDto(Element element) {
      EstateDto estateDto = new EstateDto(element.attributeValue("type"), element.attributeValue("address"),
            Integer.parseInt(element.attributeValue("price")));
      return estateDto;
   }

   @Override
   public void addEstate(Estate estate) {
      try {
         Document estateDocument = xmlFileEditor.readXMLFile(XML_FILE_PATH);
         HashMap<String, String> attributes = createHashMapFromEstate(estate);
         if (isEstatePersisted(estateDocument, attributes)) {
            return;
         }
         addNewEstateToDocument(estateDocument, attributes);
      } catch (DocumentException e) {

         e.printStackTrace();
      }
   }

   private boolean isEstatePersisted(Document existingDocument, HashMap<String, String> attributes) {
      return xmlFileEditor.elementWithCorrespondingValuesExists(existingDocument, "estates/estate/price",
            attributes.get("price"));
   }

   public HashMap<String, String> createHashMapFromEstate(Estate estate) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put("type", estate.getType());
      attributes.put("address", estate.getAddress());
      attributes.put("price", estate.getPrice().toString());

      return attributes;
   }

   private void addNewEstateToDocument(Document document, HashMap<String, String> attributes) {
      // TODO BORIS doit faire l'integration
      // xmlFileEditor.addNewElementToDocument(document, ESTATE, attributes);
   }

   @Override
   public void editEstate(Estate estate) {
      // TODO Auto-generated method stub
      // On fetch le estate puis on le re-persiste avec ses details?
   }
}
