package ca.ulaval.glo4003.b6.housematch.persistance.XMLPersistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;

public class XMLElementWriterTestIT {

   XMLElementWriter writer;

   XMLFileAccesser fileAccesser;

   XMLElementReader reader;

   final String pathToXmlFileStatic = "persistenceTestData/testData.xml";

   final String pathToXmlFileModified = "persistenceTestData/changingTestData.xml";

   final String pathToXmlFileDelete = "persistenceTestData/deleteElementTest.xml";

   Document document;

   @Mock
   RepositoryToPersistenceDto dtoContainingNewElement;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      writer = new XMLElementWriter();
      fileAccesser = new XMLFileAccesser();
      reader = new XMLElementReader();

      document = fileAccesser.readXMLFile(pathToXmlFileStatic);
      configureDtoContainingNewElement();
   }

   @Test
   public void shouldBeAbleToAddANewElementToAnXmlFile() throws DocumentException, IOException {
      // Given
      writer.addNewElementToDocument(document, dtoContainingNewElement);
      fileAccesser.formatAndWriteDocument(document, pathToXmlFileModified);

      // When
      Document modifiedDocument = fileAccesser.readXMLFile(pathToXmlFileModified);

      // Then
      assertTrue(reader.elementWithCorrespondingValueExists(modifiedDocument, "test/element/field", "added Data"));
   }

   @Test
   public void shouldBeAbleToRemoveAnExistingElement() throws DocumentException, IOException {
      // Given
      Document documentDelete = fileAccesser.readXMLFile(pathToXmlFileDelete);
      writer.addNewElementToDocument(documentDelete, dtoContainingNewElement);
      fileAccesser.formatAndWriteDocument(documentDelete, pathToXmlFileDelete);

      // When
      writer.deleteExistingElementWithCorrespondingValue(documentDelete, "test/element/field", "added Data");
      fileAccesser.formatAndWriteDocument(documentDelete, pathToXmlFileDelete);

      // Then
      assertFalse(reader.elementWithCorrespondingValueExists(documentDelete, "test/element/field", "added Data"));
   }

   private void configureDtoContainingNewElement() {
      HashMap<String, String> mapWithData = new HashMap<String, String>();
      mapWithData.put("field", "added Data");

      given(dtoContainingNewElement.getAttributes()).willReturn(mapWithData);
      given(dtoContainingNewElement.getElementName()).willReturn("element");
   }
}
