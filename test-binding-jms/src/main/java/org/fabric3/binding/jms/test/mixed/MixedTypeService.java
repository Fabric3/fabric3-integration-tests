package org.fabric3.binding.jms.test.mixed;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

@Remotable
public interface MixedTypeService { 

    ReturnType getType(String message);

    List<ReturnType> getTypes(String message);

    String getPrimitive(int val);

}