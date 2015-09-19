package ca.ulaval.glo4003.b6.housematch.user.repository;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.RepositoryToPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

@Singleton
public class XMLUserRepository implements UserDao {

   private XMLFileEditor fileEditor;

   private RepositoryToPersistenceDtoFactory dtoFactory;

   private String pathToXML = "persistence/users.xml";

   private String pathToEmailValue = "users/user/email";

   public XMLUserRepository() {
      this.fileEditor = new XMLFileEditor();
      this.dtoFactory = new RepositoryToPersistenceDtoFactory();

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

      RepositoryToPersistenceDto userDto = dtoFactory.getRepositoryDto(newUser);

      fileEditor.addNewElementToDocument(existingDocument, userDto);
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
