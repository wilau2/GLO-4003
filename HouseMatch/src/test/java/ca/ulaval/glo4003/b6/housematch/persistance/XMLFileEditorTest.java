package ca.ulaval.glo4003.b6.housematch.persistance;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class XMLFileEditorTest {

   XMLFileEditor editor;

   String pathToXmlFileStatic = "persistenceTestData/testData.xml";

   String pathToXmlFileModified = "persistenceTestData/changingTestData.xml";

   Document document;

   @Mock
   RepositoryToPersistenceDto someDto;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      editor = new XMLFileEditor();

      document = editor.readXMLFile(pathToXmlFileStatic);
      configureSomeDto();
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
   public void shouldBeAbleToVerifyTheContentOfASpecificElement() {
      // Given

      // When

      // Then
      assertTrue(editor.elementWithCorrespondingValueExists(document, "test/element/field", "Existing Data"));
   }

   @Test
   public void shouldBeAbleToAddANewElementToAnXmlFile() throws DocumentException, IOException {
      // Given
      editor.addNewElementToDocument(document, someDto);
      editor.formatAndWriteDocument(document, pathToXmlFileModified);

      // When
      Document modifiedDocument = editor.readXMLFile(pathToXmlFileModified);

      // Then
      assertTrue(editor.elementWithCorrespondingValueExists(modifiedDocument, "test/element/field", "added Data"));
   }

   @Test
   public void shouldBeAbleToAddANewNestedElementToAnXmlFile() throws DocumentException, IOException {
      // Given
      given(someDto.getElementName()).willReturn("nestedElement");
      editor.addNewNestedElementToDocument(document, "element", someDto);
      editor.formatAndWriteDocument(document, pathToXmlFileModified);

      // When
      Document modifiedDocument = editor.readXMLFile(pathToXmlFileModified);

      // Then
      assertTrue(editor.elementWithCorrespondingValueExists(modifiedDocument, "test/element/nestedElement/field",
            "added Data"));
   }

   private void configureSomeDto() {
      HashMap<String, String> mapWithData = new HashMap<String, String>();
      mapWithData.put("field", "added Data");

      given(someDto.getAttributes()).willReturn(mapWithData);
      given(someDto.getElementName()).willReturn("element");
   }
}
