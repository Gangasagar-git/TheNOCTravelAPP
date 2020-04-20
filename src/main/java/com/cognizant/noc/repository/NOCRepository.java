package com.cognizant.noc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.noc.entity.User;
@Transactional 
@Repository
public interface NOCRepository extends CrudRepository<User, Long> {
    
    List<User> findByName(String name);

    List<User> findByPhoneNo(long phoneNo);

    List<User> findByPresentCity(String city);
    
}
