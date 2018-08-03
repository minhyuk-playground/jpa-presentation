package io.presentation.jpa.entitymapping.test.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import io.presentation.jpa.entitymapping.config.ServiceConfig;
import io.presentation.jpa.entitymapping.entity.Address;
import io.presentation.jpa.entitymapping.entity.Locker;
import io.presentation.jpa.entitymapping.entity.Member;
import io.presentation.jpa.entitymapping.entity.QLocker;
import io.presentation.jpa.entitymapping.repository.LockerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@WebAppConfiguration
@Transactional
public class LockerRepositoryTest {

    @Autowired
    private LockerRepository lockerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Locker locker;

    @Before
    public void setUp() {
        this.locker = new Locker("TeamA");
    }

    @Test
    public void testDI() {
        assertTrue(lockerRepository != null);
    }

    @Test
    public void testSave() {

        //Given
        Locker locker = new Locker("LockerA");

        //When
        lockerRepository.save(locker);

        entityManager.flush();
        entityManager.clear();

        Locker findLocker = lockerRepository.findOne(locker.getLockerId());

        //Then
        assertEquals(locker, findLocker);
    }

    @Test
    public void testFindAll() {

        //Given
        int size = 100;
        IntStream.range(0, size)
                .forEach(i -> {
                    Locker locker = new Locker("Locker" + i);
                    lockerRepository.save(locker);
                });

        entityManager.clear();
        //When
        List<Locker> lockers = lockerRepository.findAll();

        //Then
        assertTrue(lockers.size() == size);
    }

    @Test
    public void testRemove() {

        //Given
        lockerRepository.save(locker);

        //When
        lockerRepository.delete(locker);
        Locker findLocker = lockerRepository.findOne(locker.getLockerId());

        //Then
        assertTrue(findLocker == null);
    }
}