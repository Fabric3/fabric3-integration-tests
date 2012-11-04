package org.fabric3.tests.spring;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 */
public class SpringSecureServiceImpl implements SecureTestService {

    @PreAuthorize("hasRole('ROLE_TELLER')")
    public void allowed() {
    }

    @PreAuthorize("hasRole('ROLE_SUPERUSER')")
    public void notAllowed() {
        throw new AssertionError("RolesAllowed not enforced");
    }

}