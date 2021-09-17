package com.example.demo.Repository;

import com.example.demo.Entity.PerSon;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.example.demo.Entity.PerSon;

@Repository

public class PersonUseImp implements PerSonUser {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public PerSon getByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using username
        Query<PerSon> theQuery = currentSession.createQuery("from PerSon where username=:uName",PerSon.class);
        theQuery.setParameter("uName", username);
        PerSon theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }

        return theUser;
    }
}
