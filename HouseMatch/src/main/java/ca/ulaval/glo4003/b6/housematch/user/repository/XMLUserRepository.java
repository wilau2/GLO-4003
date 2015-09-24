package ca.ulaval.glo4003.b6.housematch.user.repository;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

@Singleton
public class XMLUserRepository implements UserDao {

   private XMLFileEditor fileEditor;

   private RepositoryToPersistenceDtoFactory dtoFactory;

   private String pathToXML = "persistence/users.xml";

   private String pathToUsernameValue = "users/user/username";

   public XMLUserRepository() {
      this.fileEditor = new XMLFileEditor();
      this.dtoFactory = new RepositoryToPersistenceDtoFactory();

   }

   @Override
   public User findByUsername(String username) throws UserNotFoundException, CouldNotAccessDataException {
      try {
         Document usersXML = readUsersXML();
         if (!usernameAlreadyExists(usersXML, username)) {
            throw new UserNotFoundException();
         }
         return returnUserWithGivenUsername(usersXML, username);

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
         if (usernameAlreadyExists(usersXML, newUser.getUsername())) {
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

   private User returnUserWithGivenUsername(Document existingDocument, String username) {

      HashMap<String, String> attributes = fileEditor.returnAttributesOfElementWithCorrespondingValue(existingDocument,
            pathToUsernameValue, username);

      ContactInformation contactInformation = new ContactInformation(attributes.get("firstName"),
            attributes.get("lastName"), attributes.get("phoneNumber"), attributes.get("username"));

      User user = new User(attributes.get("username"), attributes.get("password"), contactInformation,
            new Role(attributes.get("role")));

      return user;
   }

   private void saveFile(Document usersXML) throws IOException {
      fileEditor.formatAndWriteDocument(usersXML, pathToXML);
   }

   private boolean usernameAlreadyExists(Document existingDocument, String username) {
      return fileEditor.elementWithCorrespondingValueExists(existingDocument, pathToUsernameValue, username);
   }

   private Document readUsersXML() throws DocumentException {
      return fileEditor.readXMLFile(pathToXML);
   }
}
