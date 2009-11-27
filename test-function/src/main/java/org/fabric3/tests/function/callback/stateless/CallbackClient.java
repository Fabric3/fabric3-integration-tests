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
package org.fabric3.tests.function.callback.stateless;

import org.oasisopen.sca.ComponentContext;
import org.oasisopen.sca.annotation.Context;
import org.oasisopen.sca.annotation.Property;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Service;

import org.fabric3.tests.function.callback.common.CallbackData;

/**
 * @version $Rev$ $Date$
 */
@Service(interfaces = {ForwardService.class, ClientService.class, CallbackService.class})
public class CallbackClient implements ForwardService, CallbackService, ClientService {
    @Property(required = false)
    protected boolean fail;

    @Reference
    protected ForwardService forwardService;

//    @Reference
//    protected ServiceReference<ForwardService> serviceReference;

    @Context
    ComponentContext context;

    public void invoke(CallbackData data) {
        forwardService.invoke(data);
    }

    public String invokeSync(CallbackData data) {
        return forwardService.invokeSync(data);
    }

    public void invokeServiceReferenceCallback(CallbackData data) {
        forwardService.invokeServiceReferenceCallback(data);
    }

    public void invokeMultipleHops(CallbackData data) {
        forwardService.invokeMultipleHops(data);
    }

    public void setErrorOnCallback() {
        fail = true;
    }

    public void onCallback(CallbackData data) {
        if (fail) {
            data.setException(new AssertionError("Wrong CallbackClient was invoked"));
        } else {
            data.callback();
        }
        data.getLatch().countDown();
    }

    public void onServiceReferenceCallback(CallbackData data) {
//        data.callback();
//        latch.countDown();
    }

    public void onSyncCallback(CallbackData data) {
        if (fail) {
            data.setException(new AssertionError("Wrong CallbackClient was invoked"));
        } else {
            data.callback();
        }
        data.callback();
    }

}
