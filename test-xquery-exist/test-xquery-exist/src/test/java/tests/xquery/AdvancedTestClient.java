/*
 * Fabric3
 * Copyright © 2008 Metaform Systems Limited
 *
 * This proprietary software may be used only connection with the Fabric3 license
 * (the “License”), a copy of which is included in the software or may be
 * obtained at: http://www.metaformsystems.com/licenses/license.html.

 * Software distributed under the License is distributed on an “as is” basis,
 * without warranties or conditions of any kind.  See the License for the
 * specific language governing permissions and limitations of use of the software.
 * This software is distributed in conjunction with other software licensed under
 * different terms.  See the separate licenses for those programs included in the
 * distribution for the permitted and restricted uses of such software.
 *
 */
package tests.xquery;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.osoa.sca.annotations.Reference;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @version $Rev$ $Date$
 */
public class AdvancedTestClient extends TestCase {

    public 
    @Reference
    AdvancedService service;

    public AdvancedTestClient() {
    }

    protected Document getDoc(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(new InputSource(new StringReader(xmlString)));
    }

    public void testXMLStream() {
        //service.xmlStream(reader)
    }

    public void testXMLDoc() throws Exception {
        String xmlString = "<root><child name=\"1\"/><child name=\"2\"/></root>";
        Document doc = getDoc(xmlString);
        doc = service.xmlDoc(doc);
        assertNotNull(doc);
        Element rootElement = doc.getDocumentElement();
        assertNotNull(rootElement);
        assertEquals("child", rootElement.getNodeName());
        Attr att = rootElement.getAttributeNode("name");
        assertNotNull(att);
        assertEquals("1", att.getValue());



    }

    public void testXMLNodeList() throws Exception {
        String xmlString = "<root><child name=\"1\"/><child name=\"2\"/></root>";
        Document doc = getDoc(xmlString);
        NodeList nodeList = service.xmlNodeList(doc.getDocumentElement().getChildNodes());
        assertNotNull(nodeList);
        assertEquals(2, nodeList.getLength());
        Node node = nodeList.item(0);
        assertTrue(node instanceof Element);
        Element el = (Element) node;
        assertEquals("child", el.getLocalName());
        Attr att = el.getAttributeNode("name");
        assertNotNull(att);
        assertEquals("1", att.getValue());
        node = nodeList.item(1);
        assertTrue(node instanceof Element);
        el = (Element) node;
        assertEquals("child", el.getLocalName());
        att = el.getAttributeNode("name");
        assertNotNull(att);
        assertEquals("2", att.getValue());
    }

    void testXMLNode() throws Exception {
        String xmlString = "<root><child name=\"1\"/><child name=\"2\"/></root>";
        Document doc = getDoc(xmlString);
        Node node = service.xmlNode(doc.getDocumentElement());
        assertNotNull(node);
        assertTrue(node instanceof Element);
        Element rootElement = (Element) node;
        assertNotNull(rootElement);
        assertEquals("child", rootElement.getNodeName());
        Attr att = rootElement.getAttributeNode("name");
        assertNotNull(att);
        assertEquals("2", att.getValue());

    }

    public void testPrimativeBoolean() {
        assertTrue(service.primativeBoolean(true));
    }

    public void testPrimativeDouble() {
        assertEquals(service.primativeDouble(1.0d), 1.0d);
    }

    public void testPrimativeFloat() {
        assertEquals(service.primativeFloat(1.0f), 1.0f);
    }

    public void testPrimativeInt() {
        assertEquals(service.primativeInteger(1), 1);
    }

    public void testPrimativeShort() {
        assertEquals(service.primativeShort((short) 1), (short) 1);
    }
    /*
    public void testPrimativeChar(){
    
    }
     */

    public void testPrimativeString() {
        assertEquals(service.primativeString("test"), "test");
    }

    public void testPrimativeVoid() {
        service.primativeVoid();
    }

    public void testPrimativeNull() {
        assertNull(service.primativeNull(null));
    }

    public void testListArray() {
        String[] array = {"array1"};
        array = service.listArray(array);
        assertNotNull(array);
        assertEquals(array.length, 2);
        assertEquals(array[0], "array1");
        assertEquals(array[1], "array2");

    }

    public void testListList() {
        List<String> list = new ArrayList<String>();
        list.add("list1");
        list = service.listList(list);
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertEquals("list1", list.get(0));
        assertEquals("list2", list.get(1));

    }

    public void testSubFunction() {
        assertEquals("subfunction subfunction", service.subFunction("subfunction"));
    }

    public void testMultiParmFunction() throws Exception {
        List list = new ArrayList();
        list.add("list");
        String xmlString = "<root><child name=\"1\"/><child name=\"2\"/></root>";
        Document doc = getDoc(xmlString);
        assertEquals("multi 1 list 1 1", service.multiParmFunction("multi", new int[]{1}, list, 1.0f, doc));
    }

    public void testReferenceFunction() {
        assertEquals("reference list", service.referenceFunction("reference", new String[]{"list"}));
    }

    /*
    public void testVarFunction() {
    assertEquals("1",service.varFunction());
    }
     */
    /*public void testXQFunction() {
        QName var = new QName("sca:property:value", "value", "value");
        Map mappings = new HashMap();
        mappings.put(var, "value");
        Node n = (Node) xqService.evaluate(mappings, Node.class);
        assertNotNull(n);
        assertEquals("adv:body", n.getNodeName());
        NodeList children = n.getChildNodes();
        assertEquals(1, children.getLength());
        n = children.item(0);
        assertEquals("adv:advFunction", n.getNodeName());
        assertTrue(n.hasAttributes());
        assertEquals("service value", n.getAttributes().item(0).getNodeValue());
    }*/
}
