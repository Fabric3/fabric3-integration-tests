package org.fabric3.tests.standalone.cluster.bindingsca.app1;

import org.fabric3.tests.standalone.cluster.bindingsca.api.TestEvent;

/**
 * @version $Rev$ $Date$
 */
public interface TestEventStream {

    void fireEvent(TestEvent event);
}
