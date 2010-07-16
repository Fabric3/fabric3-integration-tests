package org.fabric3.tests.spring;

import org.fabric3.api.annotation.security.RolesAllowed;

/**
 * @version $Rev$ $Date$
 */
public class SCASecureServiceImpl implements SecureTestService {

    @RolesAllowed("ROLE_TELLER")
    public void allowed() {
    }

    @RolesAllowed("ROLE_SUPERUSER")
    public void notAllowed() {
        throw new AssertionError("RolesAllowed not enforced");
    }

}