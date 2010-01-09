package org.fabric3.tests.function.byvalue;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

/**
 * @version $Rev$ $Date$
 */
@Remotable
public interface ByValueClient {
    
    List<String> invoke(List<String> list);

    List<String> invokeByReference(List<String> list);

}