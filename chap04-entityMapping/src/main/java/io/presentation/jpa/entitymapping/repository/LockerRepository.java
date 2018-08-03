package io.presentation.jpa.entitymapping.repository;

import io.presentation.jpa.entitymapping.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
public interface LockerRepository extends JpaRepository<Locker, Long> {

//    Locker save(Locker locker);
//
//    Locker findOne(Long lockerId);
//
//    List<Locker> findAll();
//
//    void remove(Locker locker);
}
