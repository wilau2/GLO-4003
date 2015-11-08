package ca.ulaval.glo4003.b6.housematch.persistance.XMLPersistence;

import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistance.PersistenceDto;

public class XMLFileEditorTest {

   @Mock
   XMLElementReader reader;

   @Mock
   XMLElementWriter writer;

   @Mock
   XMLFileAccesser accesser;

   @InjectMocks
   private XMLFileEditor editor;

   final String pathToXmlFileStatic = "persistenceTestData/testData.xml";

   final String pathToXmlFileModified = "persistenceTestData/changingTestData.xml";

   final String pathToXmlFileDelete = "persistenceTestData/deleteElementTest.xml";

   @Mock
   PersistenceDto dto;

   @Mock
   Document document;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void shouldDelegateReadingToAccessser() throws DocumentException {
      // Given

      // When
      editor.readXMLFile("pathToXML");

      // Then
      verify(accesser).readXMLFile("pathToXML");
   }

   @Test
   public void shouldDelegateFormatAndWriteToAccessser() throws DocumentException, IOException {
      // Given

      // When
      editor.formatAndWriteDocument(document, "pathToXML");

      // Then
      verify(accesser).formatAndWriteDocument(document, "pathToXML");
   }

   @Test
   public void shouldDelegateElementWithCorrespondingValueExistsToReader() throws DocumentException, IOException {
      // Given

      // When
      editor.elementWithCorrespondingValueExists(document, "pathToValue", "value");

      // Then
      verify(reader).elementWithCorrespondingValueExists(document, "pathToValue", "value");
   }

   @Test
   public void shouldDelegateElementWithCorrespondingValuesExistsToReader() throws DocumentException, IOException {
      // Given

      // When
      editor.elementWithCorrespondingValuesExists(document, "pathToValue", dto);

      // Then
      verify(reader).elementWithCorrespondingValuesExists(document, "pathToValue", dto);
   }

   @Test
   public void shouldDelegateReturnAttributesOfElementWithCorrespondingValueToReader()
         throws DocumentException, IOException {
      // Given

      // When
      editor.returnAttributesOfElementWithCorrespondingValue(document, "pathToValue", "value");

      // Then
      verify(reader).returnAttributesOfElementWithCorrespondingValue(document, "pathToValue", "value");
   }

   @Test
   public void shouldDelegateReturnChildAttributesOfElementWithCorrespondingValueToReader()
         throws DocumentException, IOException {
      // Given

      // When
      editor.returnChildAttributesOfElementWithCorrespondingValue(document, "pathToValue", "value", "childValue");

      // Then
      verify(reader).returnChildAttributesOfElementWithCorrespondingValue(document, "pathToValue", "value",
            "childValue");
   }

   @Test
   public void shouldDelegateGetAllElementsFromDocumentToReader() throws DocumentException, IOException {
      // Given

      // When
      editor.getAllElementsFromDocument(document, "pathToElement");

      // Then
      verify(reader).getAllElementsFromDocument(document, "pathToElement");
   }

   @Test
   public void shouldDelegateDeleteExistingElementWithCorrespondingValueToWriter()
         throws DocumentException, IOException {
      // Given

      // When
      editor.deleteExistingElementWithCorrespondingValue(document, "pathToValue", "value");

      // Then
      verify(writer).deleteExistingElementWithCorrespondingValue(document, "pathToValue", "value");
   }

   @Test
   public void shouldDelegateAddNewElementToDocumentValueToWriter() throws DocumentException, IOException {
      // Given

      // When
      editor.addNewElementToDocument(document, dto);

      // Then
      verify(writer).addNewElementToDocument(document, dto);
   }

   @Test
   public void shouldDelegateAddNewNestedElementToDocumentFromParentPathToWriter()
         throws DocumentException, IOException {
      // Given

      // When
      editor.addNewNestedElementToDocumentFromParentPath(document, dto, "wantedValue", "wantedValueName",
            "parentElementPath");

      // Then
      verify(writer).addNewNestedElementToDocumentFromParentPath(document, dto, "wantedValue", "wantedValueName",
            "parentElementPath");
   }

   @Test
   public void shouldDelegateReplaceElementToWriter() throws DocumentException, IOException {
      // Given

      // When
      editor.replaceElement(document, "pathToValue", "wantedValue", "wantedValueName", dto);

      // Then
      verify(writer).replaceElement(document, "pathToValue", "wantedValue", "wantedValueName", dto);
   }

}