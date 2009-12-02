/*
 * Fabric3
 * Copyright (c) 2009 Metaform Systems
 *
 * Fabric3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version, with the
 * following exception:
 *
 * Linking this software statically or dynamically with other
 * modules is making a combined work based on this software.
 * Thus, the terms and conditions of the GNU General Public
 * License cover the whole combination.
 *
 * As a special exception, the copyright holders of this software
 * give you permission to link this software with independent
 * modules to produce an executable, regardless of the license
 * terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided
 * that you also meet, for each linked independent module, the
 * terms and conditions of the license of that module. An
 * independent module is a module which is not derived from or
 * based on this software. If you modify this software, you may
 * extend this exception to your version of the software, but
 * you are not obligated to do so. If you do not wish to do so,
 * delete this exception statement from your version.
 *
 * Fabric3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the
 * GNU General Public License along with Fabric3.
 * If not, see <http://www.gnu.org/licenses/>.
*/
package org.fabric3.tests.wsdl.matching.singlederivedtype;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.fabric3.tests.wsdl.matching.singleelementinlinetype.SayHelloResponse;

/**
 * @version $Rev: 7770 $ $Date: 2009-10-09 12:14:53 +0200 (Fri, 09 Oct 2009) $
 */
public class HelloWorldSingleDerivedTypeStub {

    public Node invoke(Object object) throws JAXBException, ParserConfigurationException {
        if (!(object instanceof Document)) {
            throw new AssertionError("Invocation parameter was not a document");
        }
        String msg = ((Document)object).getDocumentElement().getFirstChild().getTextContent();
        SayHelloResponse response = new SayHelloResponse();
        response.setResult(msg);
        JAXBContext context = JAXBContext.newInstance(SayHelloResponse.class);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        QName qName = new QName("http://www.w3.org/2001/XMLSchema", "string");
        JAXBElement<String> element = new JAXBElement<String>(qName, String.class, msg);
        context.createMarshaller().marshal(element, document);
        return document;
    }
}