package ca.ulaval.glo4003.b6.housematch.persistance;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class XMLFileEditorTest {

   private static final String ELEMENT_NAME = "ELEMENT";

   @Mock
   private Document document;

   private Node node;

   private List<Node> nodes;

   private XMLFileEditor xmlFileEditor;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureDocumentForFetchingAllElements();

      xmlFileEditor = new XMLFileEditor();
   }

   private void configureDocumentForFetchingAllElements() {
      nodes = new ArrayList<Node>();
      nodes.add(node);

      when(document.selectNodes(ELEMENT_NAME)).thenReturn(nodes);
   }

   @Test
   public void whenGettingAllElementsFromDocumentShouldReturnListOfAllElements() {
      // Given
      int expectedNumberOfElementReturned = 1;

      // When
      List<Element> returnedElements = xmlFileEditor.getAllElementsFromDocument(document, ELEMENT_NAME);

      // Then
      assertEquals(expectedNumberOfElementReturned, returnedElements.size());
   }
}
