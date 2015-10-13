package ca.ulaval.glo4003.b6.housematch.user.repository;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;

@Singleton
public class XMLUserRepository implements UserRepository {

   private XMLFileEditor fileEditor;

   private RepositoryToPersistenceDtoFactory dtoFactory;

   private RepositoryUserAssembler assembler;

   private final String pathToXML = "persistence/users.xml";

   private final String pathToUsernameValue = "users/user/username";

   public XMLUserRepository() {
      this.fileEditor = new XMLFileEditor();
      this.dtoFactory = new RepositoryToPersistenceDtoFactory();
      this.assembler = new RepositoryUserAssembler();
   }

   @Override
   public User getUser(String username) throws CouldNotAccessUserDataException, UserNotFoundException {
      Document usersXML;
      try {
         usersXML = readUsersXML();
      } catch (DocumentException exception) {
         throw new CouldNotAccessUserDataException("Something wrong happened trying to access the data");
      }
      if (!usernameAlreadyExists(usersXML, username)) {
         throw new UserNotFoundException("No user with this username was found");
      }
      return returnUserWithGivenUsername(usersXML, username);
   }

   @Override
   public void addUser(User newUser) throws UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      try {
         Document usersXML = readUsersXML();
         if (usernameAlreadyExists(usersXML, newUser.getUsername())) {
            throw new UsernameAlreadyExistsException("This username is already used");
         } else {
            addNewUserToDocument(usersXML, newUser);
            saveFile(usersXML);
         }
      } catch (DocumentException exception) {
         throw new CouldNotAccessUserDataException("Something wrong happend trying acces the data");
      }
   }

   @Override
   public void updateUser(User user) throws CouldNotAccessUserDataException {
      try {
         Document usersXML = readUsersXML();
         fileEditor.deleteExistingElementWithCorrespondingValue(usersXML, pathToUsernameValue, user.getUsername());
         addNewUserToDocument(usersXML, user);
         saveFile(usersXML);
      } catch (DocumentException exception) {
         throw new CouldNotAccessUserDataException("Something wrong happend trying acces the data");
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
      user.setIsActive(isUserActive);

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
