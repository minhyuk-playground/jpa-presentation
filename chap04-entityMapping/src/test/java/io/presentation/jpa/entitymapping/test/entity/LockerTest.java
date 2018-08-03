package io.presentation.jpa.entitymapping.test.entity;

import io.presentation.jpa.entitymapping.entity.Address;
import io.presentation.jpa.entitymapping.entity.Locker;
import io.presentation.jpa.entitymapping.entity.LockerState;
import io.presentation.jpa.entitymapping.entity.Member;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 1.
 */
public class LockerTest {

    private Member owner;
    private Address address;
    private Locker locker;

    @Before
    public void setUp() {
        this.address = new Address("city", "street", "zipCode");
        this.owner = new Member("member01", 20, address);
        this.locker = new Locker("LockerA");
    }

    @Test
    public void testBorrowLocker() {

        //Given & When
        locker.borrowLocker(owner);

        //Then
        assertTrue(locker.getLockerState() == LockerState.IN_USED);
        assertTrue(locker.getOwner() == owner);
    }

    @Test
    public void testBorrowLockerFailureBecauseLockerInUsed() {

        //Given
        locker.borrowLocker(owner);
        Member newOwner = new Member("newMember", 100, address);

        //When
        locker.borrowLocker(newOwner);

        //Then
        assertTrue(locker.getOwner() == owner);
        assertTrue(locker.getLockerState() == LockerState.IN_USED);
    }

    @Test
    public void testResetLocker() {
        //Given
        locker.borrowLocker(owner);

        //When
        locker.resetLocker(owner);

        //Then
        assertTrue(locker.getLockerState() == LockerState.NOT_USED);
        assertTrue(locker.getOwner() == null);
    }

    @Test
    public void testResetLockerFailureBecauseLockerStateIsNOT_USED() {

        //Given & When
        locker.resetLocker(owner);

        //Then
        assertTrue(locker.getLockerState() == LockerState.NOT_USED);
        assertTrue(locker.getOwner() == null);
    }

    @Test
    public void testResetLockerFailureBecauseOwnerIsNotSame() {

        //Given
        locker.borrowLocker(owner);
        Member newOwner = new Member("newMember",40, address);

        //When
        locker.resetLocker(newOwner);

        //Then
        assertTrue(locker.getOwner() == owner);
        assertTrue(locker.getLockerState() == LockerState.IN_USED);
    }

}