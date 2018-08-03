package io.presentation.jpa.entitymapping.test.jpa;

import io.presentation.jpa.entitymapping.config.JPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
public class JPATest {

    @PersistenceUnit
    private EntityManagerFactory factory;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testDI() {
        assertTrue(factory != null);
        assertTrue(entityManager != null);
    }
}
