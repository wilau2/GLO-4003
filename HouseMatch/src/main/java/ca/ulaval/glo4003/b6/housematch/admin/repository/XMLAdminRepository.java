package ca.ulaval.glo4003.b6.housematch.admin.repository;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.admin.repository.exception.CouldNotAccesAdminDataException;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

@Singleton
public class XMLAdminRepository implements AdminRepository {

   private XMLFileEditor fileEditor;

   private String pathToXML = "persistence/admins.xml";

   private String pathToUsernameValue = "admins/admin/username";

   public XMLAdminRepository() {
      this.fileEditor = new XMLFileEditor();

   }

   @Override
   public boolean isAdmin(String username) throws CouldNotAccesAdminDataException {

      try {
         Document existingDocument = readAdminsXML();
         return fileEditor.elementWithCorrespondingValueExists(existingDocument, pathToUsernameValue, username);

      } catch (DocumentException e) {
         throw new CouldNotAccesAdminDataException();
      }

   }

   private Document readAdminsXML() throws DocumentException {
      return fileEditor.readXMLFile(pathToXML);
   }

}
