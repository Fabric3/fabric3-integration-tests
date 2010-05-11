package org.fabric3.binding.jms.test.mixed;

import java.util.List;

import org.osoa.sca.annotations.Remotable;

@Remotable
public interface MixedTypeService { 

    ReturnType getType(String message);

    List<ReturnType> getTypes(String message);

}