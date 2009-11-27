/*
 * See the NOTICE file distributed with this work for information
 * regarding copyright ownership.  This file is licensed
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package tests.rs;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @version $Rev$ $Date$
 */
@Provider
@Produces("application/entity")
@Consumes("application/entity")
public class EntityProvider implements MessageBodyReader<Entity>, MessageBodyWriter<Entity> {

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.equals(Entity.class);
    }

    public Entity readFrom(Class<Entity> type, Type genericType,
                           Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> headers,
                           InputStream entityStream) throws IOException {
        ObjectInputStream oin = new ObjectInputStream(entityStream);
        try {
            return (Entity) oin.readObject();
        } catch (ClassNotFoundException cause) {
            IOException effect = new IOException(cause.getLocalizedMessage());
            effect.initCause(cause);
            throw effect;
        }

    }

    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Entity.class.isAssignableFrom(type);
    }

    public long getSize(Entity entity, Class<?> type, Type genericType, Annotation annotations[], MediaType mediaType) {
        return -1;
    }

    public void writeTo(Entity data,
                        Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> headers,
                        OutputStream entityStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(entityStream);
        out.writeObject(data);
        out.flush();

    }

    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    public long getSize(Entity arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2) {
        // TODO Auto-generated method stub
        return false;
    }

}
