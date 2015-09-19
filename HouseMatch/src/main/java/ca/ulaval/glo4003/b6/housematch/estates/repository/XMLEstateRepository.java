package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.Collection;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

public class XMLEstateRepository implements EstateRepository {

   private static final String ESTATE = "estate";

   private XMLFileEditor xmlFileEditor;

   private String XML_FILE_PATH = "persistence/estates.xml";

   @Override
   public Collection<Estate> getAllEstates() {
      try {
         xmlFileEditor.readXMLFile(XML_FILE_PATH);
      } catch (DocumentException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return null;
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

}
