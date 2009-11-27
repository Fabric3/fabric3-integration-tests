/*
 * Fabric3
 * Copyright � 2008 Metaform Systems Limited
 *
 * This proprietary software may be used only connection with the Fabric3 license
 * (the �License�), a copy of which is included in the software or may be
 * obtained at: http://www.metaformsystems.com/licenses/license.html.

 * Software distributed under the License is distributed on an �as is� basis,
 * without warranties or conditions of any kind.  See the License for the
 * specific language governing permissions and limitations of use of the software.
 * This software is distributed in conjunction with other software licensed under
 * different terms.  See the separate licenses for those programs included in the
 * distribution for the permitted and restricted uses of such software.
 *
 */
package tests.xquery;

import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** 
 * @version $Rev$ $Date$
 */
public interface AdvancedService {
    //XMLStreamReader xmlStream(XMLStreamReader reader);
    Document xmlDoc(Document doc );
    NodeList xmlNodeList(NodeList list);
    Node xmlNode(Node node);
    boolean primativeBoolean(boolean boo);
    double primativeDouble(double dbl);
    float primativeFloat(float fl);
    int primativeInteger(int in);
    short primativeShort(short srt);
    //char primativeCharacter(char cr);
    String primativeString(String str);
    void primativeVoid();
    String primativeNull(String val);
    String [] listArray(String [] list);
    List listList(List list);
    String subFunction(String str);
    String multiParmFunction(String str, int[] list1,List list2, float flt, Document doc);
    String referenceFunction(String val, String[] list);
    //String varFunction();

}
