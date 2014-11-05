package org.fabric3.tests.rs;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.fabric3.api.annotation.scope.Composite;

/**
 *
 */
@Provider
@Composite
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public JacksonContextResolver() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}