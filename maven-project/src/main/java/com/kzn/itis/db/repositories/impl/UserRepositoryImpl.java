package com.kzn.itis.db.repositories.impl;

import com.kzn.itis.db.model.User;
import com.kzn.itis.db.repositories.UserRepository;
import com.kzn.itis.db.util.SessionUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    /**
     * Trying with EntityManager
     *
     * @param event
     * @return
     */
    // @Override
    public User addUser(User event) {
        EntityManager em = SessionUtil.getSession();

        try {
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();

        } catch (Exception e) {
            logger.error("Error: ", e);
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                // swallow
            }
        } finally {
            SessionUtil.close();
        }

        return event;
    }

    /**
     * There are several ways to get count.
     *
     * Here we implement it with the Hibernate Session - by using unwrap from JPA 2.0
     *
     * @return
     */
  //  @Override
    public long getCount() {
        EntityManager em = SessionUtil.getSession();
        Session session = em.unwrap(Session.class);

        //You can find the size of a collection without initializing it
        return (Long) session.createQuery("select count(*) from User").iterate().next();
    }
}
