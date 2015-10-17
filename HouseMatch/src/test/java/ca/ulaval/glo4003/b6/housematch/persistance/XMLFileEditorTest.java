package ca.ulaval.glo4003.b6.housematch.persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class XMLFileEditorTest {

   XMLFileEditor editor;

   final String pathToXmlFileStatic = "persistenceTestData/testData.xml";

   final String pathToXmlFileModified = "persistenceTestData/changingTestData.xml";

   final String pathToXmlFileDelete = "persistenceTestData/deleteElementTest.xml";

   Document document;

   @Mock
   RepositoryToPersistenceDto dtoContainingNewElement;

   @Mock
   RepositoryToPersistenceDto dtoContainingExistingElement;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      editor = new XMLFileEditor();

      document = editor.readXMLFile(pathToXmlFileStatic);
      configureDtoContainingNewElement();
      configureDtoContainingExistingElement();
   }

   @Test
   public void shouldBeAbleToReadTheContentOfAnXmlFile() throws DocumentException {
      // Given

      // When
      Element fileContent = editor.readXMLFile(pathToXmlFileStatic).getRootElement();

      // Then
      assertTrue(fileContent.getText().contains("This is the content of the test data"));
   }

   @Test
   public void shouldBeAbleToVerifyTheContentOfASpecificElement() throws DocumentException {
      // Given

      // When
      boolean shouldBeTrue = editor.elementWithCorrespondingValueExists(document, "test/element/field",
            "Existing Data");

      // Then
      assertTrue(shouldBeTrue);
   }

   @Test
   public void shouldBeAbleToVerifyThatAContentIsMissingElement() throws DocumentException {
      // Given

      // When
      boolean shouldBeFalse = editor.elementWithCorrespondingValueExists(document, "test/element/field",
            "Not an Existing Data");

      // Then
      assertFalse(shouldBeFalse);
   }

   @Test
   public void shouldBeAbleToVerifyEveryAttributesOfASpecificElement() throws DocumentException {
      // Given

      // When
      boolean shouldBeTrue = editor.elementWithCorrespondingValuesExists(document, "test/element",
            dtoContainingExistingElement);

      // Then
      assertTrue(shouldBeTrue);
   }

   @Test
   public void shouldBeAbleToVerifyThatSomeAttributesAreMissing() throws DocumentException {
      // Given

      // When
      boolean shouldBeFalse = editor.elementWithCorrespondingValuesExists(document, "test/element",
            dtoContainingNewElement);

      // Then
      assertFalse(shouldBeFalse);
   }

   @Test
   public void shouldBeAbleToAddANewElementToAnXmlFile() throws DocumentException, IOException {
      // Given
      editor.addNewElementToDocument(document, dtoContainingNewElement);
      editor.formatAndWriteDocument(document, pathToXmlFileModified);

      // When
      Document modifiedDocument = editor.readXMLFile(pathToXmlFileModified);

      // Then
      assertTrue(editor.elementWithCorrespondingValueExists(modifiedDocument, "test/element/field", "added Data"));
   }

   @Test
   public void shouldBeAbleToAddANewNestedElementToAnXmlFile() throws DocumentException, IOException {
      // Given
      given(dtoContainingNewElement.getElementName()).willReturn("nestedElement");
      editor.addNewNestedElementToDocument(document, "element", dtoContainingNewElement);
      editor.formatAndWriteDocument(document, pathToXmlFileModified);

      // When
      Document modifiedDocument = editor.readXMLFile(pathToXmlFileModified);

      // Then
      assertTrue(editor.elementWithCorrespondingValueExists(modifiedDocument, "test/element/nestedElement/field",
            "added Data"));
   }

   @Test
   public void shouldBeAbleToReturnAMapWithTheAttributesOfASpecifiedElement() {
      // Given

      // When
      HashMap<String, String> attributes = editor.returnAttributesOfElementWithCorrespondingValue(document,
            "test/element/field", "Existing Data");

      // Then
      assertEquals(returnAHashMapWithTheTwoExistingFields(), attributes);
   }

   @Test
   public void shouldReturnAnEmptyMapIfSpecifiedElementDoesntExists() {
      // Given

      // When
      HashMap<String, String> attributes = editor.returnAttributesOfElementWithCorrespondingValue(document,
            "test/element/field", "Not An Existing Data");

      // Then
      assertEquals(new HashMap<String, String>(), attributes);
   }

   @Test
   public void getAllElementsFromDocumentShouldReturnTheCorrectNumberOfElement() {
      // Given
      List<Element> returnedList;
      String pathToElement = "test/element";

      // When
      returnedList = editor.getAllElementsFromDocument(document, pathToElement);

      // Then
      assertEquals(1, returnedList.size());
   }

   @Test
   public void shouldBeAbleToRemoveAnExistingElement() throws DocumentException, IOException {
      // Given
      Document documentDelete = editor.readXMLFile(pathToXmlFileDelete);
      editor.addNewElementToDocument(documentDelete, dtoContainingNewElement);
      editor.formatAndWriteDocument(documentDelete, pathToXmlFileDelete);

      // When
      editor.deleteExistingElementWithCorrespondingValue(documentDelete, "test/element/field", "added Data");
      editor.formatAndWriteDocument(documentDelete, pathToXmlFileDelete);

      // Then
      assertFalse(editor.elementWithCorrespondingValueExists(documentDelete, "test/element/field", "added Data"));
   }

   @Test
   public void shouldBeAbleToReplaceAnExistingElement() throws DocumentException, IOException {
      // Given
      Document documentDelete = editor.readXMLFile(pathToXmlFileDelete);
      editor.addNewElementToDocument(documentDelete, dtoContainingNewElement);
      editor.formatAndWriteDocument(documentDelete, pathToXmlFileDelete);

      // When
      editor.replaceExistingElementWithCorrespondingValue(documentDelete, "test/element/field", "added Data",
            dtoContainingNewElement);
      editor.formatAndWriteDocument(documentDelete, pathToXmlFileDelete);

      // Then
      assertTrue(editor.elementWithCorrespondingValueExists(documentDelete, "test/element/field", "added Data"));
   }

   private void configureDtoContainingNewElement() {
      HashMap<String, String> mapWithData = new HashMap<String, String>();
      mapWithData.put("field", "added Data");

      given(dtoContainingNewElement.getAttributes()).willReturn(mapWithData);
      given(dtoContainingNewElement.getElementName()).willReturn("element");
   }

   private void configureDtoContainingExistingElement() {
      given(dtoContainingExistingElement.getAttributes()).willReturn(returnAHashMapWithTheTwoExistingFields());
      given(dtoContainingExistingElement.getElementName()).willReturn("element");
   }

   private HashMap<String, String> returnAHashMapWithTheTwoExistingFields() {
      HashMap<String, String> mapWithData = new HashMap<String, String>();
      mapWithData.put("field", "Existing Data");
      mapWithData.put("field2", "Existing Data 2");
      return mapWithData;
   }
   
}
