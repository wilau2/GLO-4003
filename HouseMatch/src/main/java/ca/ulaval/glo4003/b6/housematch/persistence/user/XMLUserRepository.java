package ca.ulaval.glo4003.b6.housematch.persistence.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.user.converter.RepositoryUserConverter;

public class XMLUserRepository implements UserRepository {

   private FileEditor fileEditor;

   private static final String PATH_TO_ALL_USER = "users/user";

   private PersistenceDtoFactory dtoFactory;

   private RepositoryUserConverter assembler;

   private final static String PATH_TO_XML = "persistence/users.xml";

   private final static String PATH_TO_USERNAME_VALUE = "users/user/username";

   @Inject
   public XMLUserRepository(PersistenceDtoFactory persistenceDtoFactory,
         RepositoryUserConverter repositoryUserConverter, FileEditor fileEditor) {
      this.dtoFactory = persistenceDtoFactory;
      this.assembler = repositoryUserConverter;
      this.fileEditor = fileEditor;
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
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }
   }

   @Override
   public void updateUser(User user) throws CouldNotAccessDataException {
      try {
         Document usersXML = readUsersXML();
         fileEditor.deleteExistingElementWithCorrespondingValue(usersXML, PATH_TO_USERNAME_VALUE, user.getUsername());
         addNewUserToDocument(usersXML, user);
         saveFile(usersXML);
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }
   }

   private void addNewUserToDocument(Document existingDocument, User newUser) {

      PersistenceDto userDto = dtoFactory.getRepositoryDto(newUser);

      fileEditor.addNewElementToDocument(existingDocument, userDto);
   }

   private User returnUserWithGivenUsername(Document existingDocument, String username) {
      HashMap<String, String> attributes = fileEditor.returnAttributesOfElementWithCorrespondingValue(existingDocument,
            PATH_TO_USERNAME_VALUE, username);

      User user = assembler.assembleUserFromAttributes(attributes);

      boolean isUserActive = Boolean.parseBoolean(attributes.get("isActive"));
      user.setActive(isUserActive);

      return user;
   }

   private void saveFile(Document usersXML) throws DocumentException {
      try {
         fileEditor.formatAndWriteDocument(usersXML, PATH_TO_XML);
      } catch (IOException e) {
         throw new DocumentException("Could not access the specified file");
      }
   }

   private boolean usernameAlreadyExists(Document existingDocument, String username) {
      return fileEditor.elementWithCorrespondingValueExists(existingDocument, PATH_TO_USERNAME_VALUE, username);
   }

   private Document readUsersXML() throws DocumentException {
      return fileEditor.readXMLFile(PATH_TO_XML);

   }

   @Override
   public List<User> getAllUser() throws DocumentException {

      Document userDocument = readUsersXML();
      List<Element> allElementsFromDocument = fileEditor.getAllElementsFromDocument(userDocument, PATH_TO_ALL_USER);

      List<User> users = new ArrayList<User>();

      for (Element userElement : allElementsFromDocument) {
         User userFromElement = assembler.assembleUserFromElement(userElement);
         users.add(userFromElement);
      }

      return users;
   }

}
