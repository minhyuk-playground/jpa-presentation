package io.presentation.jpa.entitymapping.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created By Minhyuk Yoon on 2018. 8. 1.
 */
@Table(name = "locker")
@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id")
    private Long lockerId;

    @Column(name = "locker_name", length = 50, nullable = false)
    private String lockerName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "locker_state")
    private LockerState lockerState;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "member_id"
    )
    private Member owner;

    protected Locker() {
    }

    public Locker(String lockerName) {
        this.lockerName = lockerName;
        this.lockerState = LockerState.NOT_USED;
    }

    public void borrowLocker(Member owner) {
        if (lockerState.isBorrowable()) {
            this.owner = owner;
            this.lockerState = LockerState.IN_USED;
        }
    }

    public void resetLocker(Member owner) {
        if (!lockerState.isBorrowable() && isSameOwner(owner)) {
            lockerState = LockerState.NOT_USED;
            this.owner = null;
        }
    }

    private boolean isSameOwner(Member owner) {
        return this.owner == owner;
    }

    public Long getLockerId() {
        return lockerId;
    }

    public String getLockerName() {
        return lockerName;
    }

    public LockerState getLockerState() {
        return lockerState;
    }

    public Member getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locker locker = (Locker) o;
        return Objects.equals(lockerId, locker.lockerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lockerId);
    }
}