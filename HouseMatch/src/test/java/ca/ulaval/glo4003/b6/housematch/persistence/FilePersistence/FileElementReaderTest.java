package ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;

public class FileElementReaderTest {

   FileElementReader reader;

   final String pathToXmlFileStatic = "persistenceTestData/testData.xml";

   final String pathToXmlFileStaticNested = "persistenceTestData/testDataNested.xml";

   Document document;

   FileAccesser fileAccesser;

   @Mock
   PersistenceDto dtoContainingNewElement;

   @Mock
   PersistenceDto dtoContainingExistingElement;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      reader = new FileElementReader();
      fileAccesser = new FileAccesser();

      document = fileAccesser.readXMLFile(pathToXmlFileStatic);
      configureDtoContainingNewElement();
      configureDtoContainingExistingElement();
   }

   @Test
   public void shouldBeAbleToVerifyTheContentOfASpecificElement() throws DocumentException {
      // Given

      // When
      boolean shouldBeTrue = reader.elementWithCorrespondingValueExists(document, "test/element/field",
            "Existing Data");

      // Then
      assertTrue(shouldBeTrue);
   }

   @Test
   public void shouldBeAbleToVerifyThatAContentIsMissingElement() throws DocumentException {
      // Given

      // When
      boolean shouldBeFalse = reader.elementWithCorrespondingValueExists(document, "test/element/field",
            "Not an Existing Data");

      // Then
      assertFalse(shouldBeFalse);
   }

   @Test
   public void shouldBeAbleToVerifyEveryAttributesOfASpecificElement() throws DocumentException {
      // Given

      // When
      boolean shouldBeTrue = reader.elementWithCorrespondingValuesExists(document, "test/element",
            dtoContainingExistingElement);

      // Then
      assertTrue(shouldBeTrue);
   }

   @Test
   public void shouldBeAbleToVerifyThatSomeAttributesAreMissing() throws DocumentException {
      // Given

      // When
      boolean shouldBeFalse = reader.elementWithCorrespondingValuesExists(document, "test/element",
            dtoContainingNewElement);

      // Then
      assertFalse(shouldBeFalse);
   }

   @Test
   public void shouldBeAbleToReturnAMapWithTheAttributesOfASpecifiedElement() {
      // Given

      // When
      HashMap<String, String> attributes = reader.returnAttributesOfElementWithCorrespondingValue(document,
            "test/element/field", "Existing Data");

      // Then
      assertEquals(returnAHashMapWithTheTwoExistingFields(), attributes);
   }

   @Test
   public void shouldReturnAnEmptyMapIfSpecifiedElementDoesntExists() {
      // Given

      // When
      HashMap<String, String> attributes = reader.returnAttributesOfElementWithCorrespondingValue(document,
            "test/element/field", "Not An Existing Data");

      // Then
      assertEquals(new HashMap<String, String>(), attributes);
   }

   @Test
   public void shouldBeAbleToReturnAMapWithTheChildAttributesOfElement() throws DocumentException {
      // Given
      Document nestedDocument = fileAccesser.readXMLFile(pathToXmlFileStaticNested);

      // When
      HashMap<String, String> attributes = reader.returnChildAttributesOfElementWithCorrespondingValue(nestedDocument,
            "test/element/field", "field value", "nested");

      // Then
      assertEquals(returnAHashMapWithNestedValue(), attributes);
   }

   @Test
   public void shouldReturnAnEmptyMapIfSpecifiedChildElementDoesntExists() throws DocumentException {
      // Given
      Document nestedDocument = fileAccesser.readXMLFile(pathToXmlFileStaticNested);

      // When
      HashMap<String, String> attributes = reader.returnChildAttributesOfElementWithCorrespondingValue(nestedDocument,
            "test/element/field", "not the right value", "nested");

      // Then
      assertEquals(new HashMap<String, String>(), attributes);
   }

   @Test
   public void getAllElementsFromDocumentShouldReturnTheCorrectNumberOfElement() {
      // Given
      List<Element> returnedList;
      String pathToElement = "test/element";

      // When
      returnedList = reader.getAllElementsFromDocument(document, pathToElement);

      // Then
      assertEquals(1, returnedList.size());
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

   private HashMap<String, String> returnAHashMapWithNestedValue() {
      HashMap<String, String> mapWithData = new HashMap<String, String>();
      mapWithData.put("nestedField", "nested");
      return mapWithData;
   }
}
