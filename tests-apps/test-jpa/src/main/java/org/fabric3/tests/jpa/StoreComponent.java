package org.fabric3.tests.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.oasisopen.sca.annotation.ManagedTransaction;
import org.oasisopen.sca.annotation.Scope;

/**
 *
 */
@Scope("COMPOSITE")
@ManagedTransaction
public class StoreComponent implements Store {
    @PersistenceContext(unitName = "message")
    protected EntityManager em;

    public void create(String creator) {
        Message message = new Message();
        message.setCreator(creator);
        em.persist(message);
        try {
            em.flush();
        } catch (PersistenceException e) {
            e.printStackTrace();
           // throw new RuntimeException(e);
        }
    }
}

