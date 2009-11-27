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
package org.fabric3.tests.binding.metro.wsdl;

import java.io.StringReader;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.fabric3.spi.invocation.Message;
import org.fabric3.spi.invocation.MessageImpl;
import org.fabric3.spi.wire.InvocationChain;
import org.fabric3.spi.wire.Wire;
import org.fabric3.tests.binding.metro.helloworld.HelloFault;
import org.fabric3.tests.binding.metro.helloworld.HelloFaultException;


/**
 * @version $Rev: 7770 $ $Date: 2009-10-09 12:14:53 +0200 (Fri, 09 Oct 2009) $
 */
public class HelloStub {
//    private static String XML_BARE =
//            "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request xmlns=\"http://wsdl.metro.binding.tests.fabric3.org/\">callout</request>";

    private static String XML =
            "<ns2:hello xmlns:ns2=\"http://wsdl.metro.binding.tests.fabric3.org/\"><arg0>callout</arg0></ns2:hello>";

    private Wire wire;
    private TransformerFactory transformerFactory;

    public HelloStub() {
        transformerFactory = TransformerFactory.newInstance();
    }

    public void setWire(String name, Wire wire) {
        this.wire = wire;
    }

    public Node invoke(Object object) throws JAXBException, ParserConfigurationException, HelloFaultException {
        if (!(object instanceof Document)) {
            throw new AssertionError("Invocation parameter was not a document");
        }
        Document document = (Document) object;
        String request = document.getDocumentElement().getFirstChild().getTextContent();
        if ("fault".equals(request)) {
            HelloFault bean = new HelloFault();
            bean.setCode("200");
            bean.setDetail("Application exception");
            throw new HelloFaultException("Application fault", bean);
        } else if ("callout".equals(request)) {
            // test invocation using a WSDL contract
            InvocationChain chain = wire.getInvocationChains().get(0);
            Message message = new MessageImpl();
            try {
                Transformer transformer = transformerFactory.newTransformer();
                DOMResult result = new DOMResult();
                transformer.transform(new StreamSource(new StringReader(XML)), result);
                message.setBody(new Object[]{result.getNode()});

                Message messageResponse = chain.getHeadInterceptor().invoke(message);

                if (!(messageResponse.getBody() instanceof Document)) {
                    throw new AssertionError("Pojo invocation did not return a document");
                }
                if (!"callout".equals(((Document) messageResponse.getBody()).getDocumentElement().getFirstChild().getTextContent())) {
                    throw new AssertionError("Invalid return value");
                }
                return document;
            } catch (TransformerException e) {
                e.printStackTrace();
            }


        }
        return document;
    }
}