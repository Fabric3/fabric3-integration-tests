package org.fabric3.tests.function.byvalue;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 */
@Remotable
public interface RemoteService {

    List<String> invoke(List<String> list);

}
