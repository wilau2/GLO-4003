package ca.ulaval.glo4003.b6.housematch.user.repository;

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

import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.RepositoryToPersistenceUserDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class XMLUserRepositoryTest {

   private String existingEmail = "an existing Email";

   private String correctPassword = "the corresponding password";

   private String newEmail = "a new Email";

   private String correctPathToFile = "persistence/users.xml";

   private String correctPathToEmailValue = "users/user/email";

   @Mock
   private User user;

   @Mock
   private XMLFileEditor editor;

   @Mock
   private Document usedDocument;

   @Mock
   private RepositoryToPersistenceDtoFactory dtoFactory;

   @Mock
   private RepositoryToPersistenceUserDto userDto;

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
   public void whenFindingByEmailShouldReadTheCorrectFile()
         throws DocumentException, UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      repository.findByEmail(existingEmail);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenFindingByEmailShouldLookIfUsersExists() throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      repository.findByEmail(existingEmail);

      // Then
      verify(editor).elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, existingEmail);
   }

   @Test
   public void whenFindingByEmailShouldReturnAUserWithTheCorrectEmail()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      User returnedUser = repository.findByEmail(existingEmail);

      // Then
      assertEquals(returnedUser.getEmail(), existingEmail);
   }

   @Test
   public void whenFindingByEmailShouldReturnAUserWithTheCorrectPassword()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      User returnedUser = repository.findByEmail(existingEmail);

      // Then
      assertEquals(returnedUser.getPassword(), correctPassword);
   }

   @Test
   public void whenAddingUserShouldReadTheCorrectFile()
         throws DocumentException, UserAlreadyExistsException, CouldNotAccessDataException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenAddingUserShouldLookIfUsersExists() throws UserAlreadyExistsException, CouldNotAccessDataException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, newEmail);
   }

   @Test
   public void whenAddingUserShouldCreateNewDto() throws UserAlreadyExistsException, CouldNotAccessDataException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(dtoFactory).getRepositoryDto(user);
   }

   @Test
   public void whenAddingUserShouldAddNewUserToXMLWithDto()
         throws UserAlreadyExistsException, CouldNotAccessDataException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
   }

   @Test
   public void whenAddingUserShouldWriteToTheRightFile()
         throws IOException, UserAlreadyExistsException, CouldNotAccessDataException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).formatAndWriteDocument(usedDocument, correctPathToFile);
   }

   @Test(expected = UserNotFoundException.class)
   public void whenFindingByEmailShouldReturnExceptionIfEmailDoesNotExist()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given A new email

      // When
      repository.findByEmail(newEmail);

      // Then Exception is thrown
   }

   @Test(expected = UserAlreadyExistsException.class)
   public void whenAddingUserShouldReturnExceptionIfEmailExist()
         throws UserAlreadyExistsException, CouldNotAccessDataException {
      // Given An existing user

      // When
      repository.add(user);

      // Then Exception is thrown
   }

   private void configureFactory() {
      given(dtoFactory.getRepositoryDto(user)).willReturn(userDto);
   }

   private void configureUser() {
      given(user.getUsername()).willReturn("username");
      given(user.getFirstName()).willReturn("firstName");
      given(user.getLastName()).willReturn("lastName");
      given(user.getPhoneNumber()).willReturn("phoneNumber");
      given(user.getEmail()).willReturn(existingEmail);
      given(user.getPassword()).willReturn(correctPassword);
   }

   private void configureDifferentUser() {
      given(user.getUsername()).willReturn("username2");
      given(user.getFirstName()).willReturn("firstName2");
      given(user.getLastName()).willReturn("lastName2");
      given(user.getPhoneNumber()).willReturn("phoneNumber2");
      given(user.getEmail()).willReturn(newEmail);
      given(user.getPassword()).willReturn("newPassword");
   }

   private void configureEditor() throws DocumentException {
      given(editor.readXMLFile(correctPathToFile)).willReturn(usedDocument);
      given(editor.elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, existingEmail))
            .willReturn(true);

      HashMap<String, String> mapWithUserData = new HashMap<String, String>();
      mapWithUserData.put("username", "username");
      mapWithUserData.put("password", "firstName");
      mapWithUserData.put("email", "lastName");
      mapWithUserData.put("password", "phoneNumber");
      mapWithUserData.put("email", existingEmail);
      mapWithUserData.put("password", correctPassword);

      given(editor.returnAttributesOfElementWithCorrespondingValue(usedDocument, correctPathToEmailValue,
            existingEmail)).willReturn(mapWithUserData);
   }

}
