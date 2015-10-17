package ca.ulaval.glo4003.b6.housematch.persistance.user;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistance.user.converter.RepositoryUserConverter;

@Singleton
public class XMLUserRepository implements UserRepository {

   private XMLFileEditor fileEditor;

   private RepositoryToPersistenceDtoFactory dtoFactory;

   private RepositoryUserConverter assembler;

   private final String pathToXML = "persistence/users.xml";

   private final String pathToUsernameValue = "users/user/username";

   public XMLUserRepository() {
      this.fileEditor = new XMLFileEditor();
      this.dtoFactory = new RepositoryToPersistenceDtoFactory();
      this.assembler = new RepositoryUserConverter();
   }

   @Override
   public User getUser(String username) throws CouldNotAccessDataException, UserNotFoundException {
      Document usersXML;
      try {
         usersXML = readUsersXML();
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happened trying to access the data", exception);
      }
      if (!usernameAlreadyExists(usersXML, username)) {
         throw new UserNotFoundException("No user with this username was found");
      }
      return returnUserWithGivenUsername(usersXML, username);
   }

   @Override
   public void addUser(User newUser) throws UsernameAlreadyExistsException, CouldNotAccessDataException {
      try {
         Document usersXML = readUsersXML();
         if (usernameAlreadyExists(usersXML, newUser.getUsername())) {
            throw new UsernameAlreadyExistsException("This username is already used");
         } else {
            addNewUserToDocument(usersXML, newUser);
            saveFile(usersXML);
         }
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happend trying acces the data", exception);
      }
   }

   @Override
   public void updateUser(User user) throws CouldNotAccessDataException {
      try {
         Document usersXML = readUsersXML();
         fileEditor.deleteExistingElementWithCorrespondingValue(usersXML, pathToUsernameValue, user.getUsername());
         addNewUserToDocument(usersXML, user);
         saveFile(usersXML);
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happend trying acces the data", exception);
      }
   }

   private void addNewUserToDocument(Document existingDocument, User newUser) {

      RepositoryToPersistenceDto userDto = dtoFactory.getRepositoryDto(newUser);

      fileEditor.addNewElementToDocument(existingDocument, userDto);
   }

   private User returnUserWithGivenUsername(Document existingDocument, String username) {
      HashMap<String, String> attributes = fileEditor.returnAttributesOfElementWithCorrespondingValue(existingDocument,
            pathToUsernameValue, username);

      User user = assembler.assembleUserFromAttributes(attributes);

      boolean isUserActive = Boolean.parseBoolean(attributes.get("isActive"));
      user.setActive(isUserActive);

      return user;
   }

   private void saveFile(Document usersXML) throws DocumentException {
      try {
         fileEditor.formatAndWriteDocument(usersXML, pathToXML);
      } catch (IOException e) {
         throw new DocumentException("Could not access the specified file");
      }
   }

   private boolean usernameAlreadyExists(Document existingDocument, String username) {
      return fileEditor.elementWithCorrespondingValueExists(existingDocument, pathToUsernameValue, username);
   }

   private Document readUsersXML() throws DocumentException {
      return fileEditor.readXMLFile(pathToXML);
   }
}
