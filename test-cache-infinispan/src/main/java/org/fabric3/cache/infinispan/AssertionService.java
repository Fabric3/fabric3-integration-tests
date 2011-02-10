package org.fabric3.cache.infinispan;

import org.oasisopen.sca.annotation.Remotable;

/**
 * @version $Rev$ $Date$
 */
@Remotable
public interface AssertionService {

    boolean assertCount(int exceptedCount);

}
