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
package org.fabric3.tests.implementation.wsdl.runtime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import javax.xml.namespace.QName;

import org.fabric3.api.annotation.monitor.MonitorLevel;
import org.fabric3.host.Fabric3RuntimeException;
import org.fabric3.spi.builder.WiringException;
import org.fabric3.spi.component.Component;
import org.fabric3.spi.wire.Wire;

/**
 * @version $Rev$ $Date$
 */
public class TestWsdlComponent implements Component {
    private URI classLoaderId;
    private URI uri;
    private Object instance;
    private Method invokeMethod;
    private Method setWireMethod;
    private MonitorLevel level = MonitorLevel.INFO;

    public TestWsdlComponent(URI uri, Object instance) {
        this.uri = uri;
        this.instance = instance;
        try {
            invokeMethod = instance.getClass().getMethod("invoke", Object.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Stub class must have an invoke(Object) method");
        }
        try {
            setWireMethod = instance.getClass().getMethod("setWire", String.class, Wire.class);
        } catch (NoSuchMethodException e) {
            // no method defined
        }

    }

    public QName getDeployable() {
        return null;
    }

    public URI getUri() {
        return uri;
    }

    public URI getClassLoaderId() {
        return classLoaderId;
    }

    public void setClassLoaderId(URI classLoaderId) {
        this.classLoaderId = classLoaderId;
    }

    public int getLifecycleState() {
        return 0;
    }

    public void start() throws Fabric3RuntimeException {

    }

    public void stop() throws Fabric3RuntimeException {

    }

    public String getName() {
        return uri.toString();
    }

    public MonitorLevel getLevel() {
        return level;
    }

    public void setLevel(MonitorLevel level) {
        this.level = level;
    }

    public void setWire(String name, Wire wire) throws WiringException {
        if (setWireMethod == null) {
            throw new WiringException("Stub class " + instance.getClass() + " must have a setWire(String, Wire) method if it is wired");
        }
        try {
            setWireMethod.invoke(instance, name, wire);
        } catch (IllegalAccessException e) {
            throw new WiringException(e);
        } catch (InvocationTargetException e) {
            throw new WiringException(e);
        }

    }

    public Object invoke(Object object) {
        try {
            return invokeMethod.invoke(instance, object);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof Exception && !(e.getCause() instanceof RuntimeException)) {
                return e.getCause();
            }
            throw new AssertionError(e);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return e;
        }
    }

}
