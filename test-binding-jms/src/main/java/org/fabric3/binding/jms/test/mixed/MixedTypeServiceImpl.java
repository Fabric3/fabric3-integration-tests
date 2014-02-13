package org.fabric3.binding.jms.test.mixed;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MixedTypeServiceImpl implements MixedTypeService {

    public ReturnType getType(String message) {
        return new ReturnType();
    }

    public List<ReturnType> getTypes(String message) {
        List<ReturnType> types = new ArrayList<>();
        types.add(new ReturnType());
        return types;
    }

    public String getPrimitive(int val) {
        return String.valueOf(val);
    }
}
