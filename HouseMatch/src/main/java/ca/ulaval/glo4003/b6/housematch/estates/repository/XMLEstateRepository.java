package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.Collection;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

public class XMLEstateRepository implements EstateRepository {

   private static final String ESTATE = "estate";

   private XMLFileEditor xmlFileEditor;

   private RepositoryToPersistenceDtoFactory dtoFactory;

   private String XML_FILE_PATH = "persistence/estates.xml";

   @Override
   public Collection<Estate> getAllEstate() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void addEstate(Estate estate) {
      try {
         Document estateDocument = xmlFileEditor.readXMLFile(XML_FILE_PATH);
         if (isEstatePersisted(estateDocument, estate)) {
            return;
         }
         addNewEstateToDocument(estateDocument, estate);
      } catch (DocumentException e) {

         e.printStackTrace();
      }
   }

   private boolean isEstatePersisted(Document existingDocument, Estate estate) {

      RepositoryToPersistenceDto estateDto = dtoFactory.getRepositoryDto(estate);
      return xmlFileEditor.elementWithCorrespondingValuesExists(existingDocument, "estates/estate", estateDto);
   }

   private void addNewEstateToDocument(Document document, Estate estate) {
      RepositoryToPersistenceDto estateDto = dtoFactory.getRepositoryDto(estate);

      xmlFileEditor.addNewElementToDocument(document, estateDto);
   }
}
