package ca.ulaval.glo4003.b6.housematch.persistance.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistance.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLPersistence.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistance.user.converter.RepositoryUserConverter;

public class XMLUserRepositoryTest {

   private final String existingUsername = "an existing Username";

   private final String correctPassword = "the corresponding password";

   private final String newUsername = "a new Username";

   private final String correctPathToFile = "persistence/users.xml";

   private final String correctPathToUsernameValue = "users/user/username";

   @Mock
   private User user;

   @Mock
   private XMLFileEditor editor;

   @Mock
   private Document usedDocument;

   @Mock
   private PersistenceDtoFactory dtoFactory;

   @Mock
   private UserPersistenceDto userDto;

   @Mock
   private RepositoryUserConverter assembler;

   @InjectMocks
   private XMLUserRepository repository;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureEditor();
      configureUser();
      configureFactory();
   }

   @Test
   public void whenFindingByUsernameShouldReadTheCorrectFile()
         throws DocumentException, CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      repository.getUser(existingUsername);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenFindingByUsernameShouldLookIfUsersExists()
         throws CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      repository.getUser(existingUsername);

      // Then
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectUsername()
         throws CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      User returnedUser = repository.getUser(existingUsername);

      // Then
      assertEquals(returnedUser.getUsername(), existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectPassword()
         throws CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      User returnedUser = repository.getUser(existingUsername);

      // Then
      assertEquals(returnedUser.getPassword(), correctPassword);
   }

   @Test
   public void whenAddingUserShouldReadTheCorrectFile()

   throws DocumentException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenAddingUserShouldLookIfUsersExists()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, newUsername);
   }

   @Test
   public void whenAddingUserShouldCreateNewDto() throws UsernameAlreadyExistsException, CouldNotAccessDataException {
      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(dtoFactory).getRepositoryDto(user);
   }

   @Test
   public void whenAddingUserShouldAddNewUserToXMLWithDto()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
   }

   @Test
   public void whenAddingUserShouldWriteToTheRightFile()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).formatAndWriteDocument(usedDocument, correctPathToFile);
   }

   @Test
   public void whenUpdatingAUserShouldCallTheDeleteMethodWithTheRightArguments()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given

      // When
      repository.updateUser(user);

      // Then
      verify(editor).deleteExistingElementWithCorrespondingValue(usedDocument, correctPathToUsernameValue,
            existingUsername);
   }

   @Test
   public void whenUpdatingAUserShouldCallAddUserWithTheRightArguments()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given

      // When
      repository.updateUser(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
   }

   @Test
   public void whenSettingActivityShouldLookForTheRightUser()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotFoundException {
      // Given

      // When
      repository.setUserActivity(existingUsername, true);

      // Then
      verify(editor).returnAttributesOfElementWithCorrespondingValue(usedDocument, correctPathToUsernameValue,
            existingUsername);
   }

   @Test
   public void whenSettingActivityShouldModifyTheUserAttribute()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotFoundException {
      // Given

      // When
      repository.setUserActivity(existingUsername, false);

      // Then
      verify(user).setActive(false);
   }

   @Test
   public void whenSettingActivityShouldAddTheUser()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotFoundException {
      // Given

      // When
      repository.setUserActivity(existingUsername, false);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
   }

   @Test(expected = UserNotFoundException.class)
   public void whenFindingByUsernameShouldReturnExceptionIfUsernameDoesNotExist()
         throws CouldNotAccessDataException, UserNotFoundException {
      // Given A new username

      // When
      repository.getUser(newUsername);

      // Then Exception is thrown
   }

   @Test(expected = UsernameAlreadyExistsException.class)
   public void whenAddingUserShouldReturnExceptionIfUsernameExist()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given An existing user

      // When
      repository.addUser(user);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void whenFindingByUsernameShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessDataException, UserNotFoundException, DocumentException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

      // When
      repository.getUser(existingUsername);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void whenUpdatingShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessDataException, UserNotFoundException, DocumentException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

      // When
      repository.updateUser(user);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void whenAddingUserShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessDataException, UserNotFoundException, DocumentException, UsernameAlreadyExistsException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

      // When
      repository.addUser(user);

      // Then Exception is thrown
   }

   private void configureFactory() {
      given(dtoFactory.getRepositoryDto(user)).willReturn(userDto);
   }

   private void configureUser() {
      given(user.getUsername()).willReturn(existingUsername);
      given(user.getPassword()).willReturn(correctPassword);
   }

   private void configureDifferentUser() {
      given(user.getUsername()).willReturn(newUsername);
      given(user.getPassword()).willReturn("newPassword");
   }

   private void configureEditor() throws DocumentException {
      given(editor.readXMLFile(correctPathToFile)).willReturn(usedDocument);
      given(editor.elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, existingUsername))
            .willReturn(true);

      HashMap<String, String> mapWithUserData = new HashMap<String, String>();
      mapWithUserData.put("username", existingUsername);
      mapWithUserData.put("password", correctPassword);
      mapWithUserData.put("firstName", "firstName");
      mapWithUserData.put("lastName", "lastName");
      mapWithUserData.put("role", "role");
      mapWithUserData.put("phoneNumber", "phoneNumber");
      mapWithUserData.put("email", "email");
      mapWithUserData.put("isActive", "true");

      given(editor.returnAttributesOfElementWithCorrespondingValue(usedDocument, correctPathToUsernameValue,
            existingUsername)).willReturn(mapWithUserData);

      given(assembler.assembleUserFromAttributes(mapWithUserData)).willReturn(user);
   }
}
