package com.ps.nextgen.vaccination_drive.repository.dao;

import com.ps.nextgen.vaccination_drive.repository.impl.EmployeeDetails;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepo extends MongoRepository<EmployeeDetails,Integer> {

    public Optional<EmployeeDetails> findById(Integer id);
   

}
    

