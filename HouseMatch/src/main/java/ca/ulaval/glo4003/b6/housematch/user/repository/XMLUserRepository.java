package ca.ulaval.glo4003.b6.housematch.user.repository;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

@Repository
@Singleton
public class XMLUserRepository implements UserRepository {

   private XMLFileEditor fileEditor;

   private String pathToXML = "persistence/users.xml";

   private String pathToEmailValue = "users/user/email";

   public XMLUserRepository() { // should have a bean to give him the two paths
      this.fileEditor = new XMLFileEditor();
   }

   @Override
   public User findByEmail(String email) throws UserNotFoundException, CouldNotAccessDataException {
      try {
         Document usersXML = readUsersXML();
         if (!emailAlreadyExists(usersXML, email)) {
            throw new UserNotFoundException();
         }
         return returnUserWithGivenEmail(usersXML, email);

      } catch (UserNotFoundException userNotFoundException) {
         throw userNotFoundException;
      } catch (Exception exception) {
         exception.printStackTrace();
         throw new CouldNotAccessDataException();
      }
   }

   @Override
   public void add(User newUser) throws UserAlreadyExistsException, CouldNotAccessDataException {
      try {
         Document usersXML = readUsersXML();
         if (emailAlreadyExists(usersXML, newUser.getEmail())) {
            throw new UserAlreadyExistsException();
         } else {
            addNewUserToDocument(usersXML, newUser);
            saveFile(usersXML);
         }
      } catch (UserAlreadyExistsException userExists) {
         throw userExists;
      } catch (Exception exception) {
         exception.printStackTrace();
         throw new CouldNotAccessDataException();
      }
   }

   private void addNewUserToDocument(Document existingDocument, User newUser) {
      String elementName = "user";

      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put("username", newUser.getUsername());
      attributes.put("firstName", newUser.getFirstName());
      attributes.put("lastName", newUser.getLastName());
      attributes.put("phoneNumber", newUser.getPhoneNumber());
      attributes.put("email", newUser.getEmail());
      attributes.put("password", newUser.getPassword());

      fileEditor.addNewElementToDocument(existingDocument, elementName, attributes);
   }

   private User returnUserWithGivenEmail(Document existingDocument, String email) {

      HashMap<String, String> attributes = fileEditor.returnAttributesOfElementWithCorrespondingValue(existingDocument,
            pathToEmailValue, email);

      User user = new User(attributes.get("username"), attributes.get("firstName"), attributes.get("lastName"),
            attributes.get("phoneNumber"), attributes.get("email"), attributes.get("password"));

      return user;
   }

   private void saveFile(Document usersXML) throws IOException {
      fileEditor.formatAndWriteDocument(usersXML, pathToXML);
   }

   private boolean emailAlreadyExists(Document existingDocument, String email) {
      return fileEditor.elementWithCorrespondingValuesExists(existingDocument, pathToEmailValue, email);
   }

   private Document readUsersXML() throws DocumentException {
      return fileEditor.readXMLFile(pathToXML);
   }
}
