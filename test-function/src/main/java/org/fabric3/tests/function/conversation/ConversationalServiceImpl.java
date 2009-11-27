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
package org.fabric3.tests.function.conversation;

import org.oasisopen.sca.annotation.Scope;
import org.osoa.sca.annotations.ConversationID;
import org.osoa.sca.annotations.EndsConversation;

/**
 * @version $Rev$ $Date$
 */
@Scope("CONVERSATION")
public class ConversationalServiceImpl implements ConversationalService {
    private String value;
    private Object conversationId;

//    @ConversationID
//    protected Object fieldConversationId;

    // FIXME the introspection framwork does not support injecting context types (RequestContext, ComponentContext) and conversation ids on multiple
    // sites.  

    @ConversationID
    public void setConversationId(Object conversationId) {
        this.conversationId = conversationId;
    }

    public Object getConversationId() {
        return conversationId;
    }

    public Object getFieldConversationId() {
        return null;
        //return fieldConversationId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @EndsConversation
    public String end() {
        return value;
    }
}
