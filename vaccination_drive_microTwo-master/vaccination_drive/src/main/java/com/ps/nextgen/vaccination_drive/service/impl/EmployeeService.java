package com.ps.nextgen.vaccination_drive.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ps.nextgen.vaccination_drive.repository.impl.EmployeeDetails;
import com.ps.nextgen.vaccination_drive.repository.dao.EmployeeDetailsRepo;

import com.ps.nextgen.vaccination_drive.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeDetailsRepo eRepo;

    @Override
    public List<EmployeeDetails> getAllEmployee(){

        List<EmployeeDetails> list=(List<EmployeeDetails>)this.eRepo.findAll();
        return list;

    }

    @Override
    public EmployeeDetails getEmployeeById(int id) {
      Optional<EmployeeDetails> op=this.eRepo.findById(id);
      EmployeeDetails emp=op.get();
        return emp;
    }

    @Override
    public EmployeeDetails addEmployee(EmployeeDetails emp) {
        EmployeeDetails saved=this.eRepo.save(emp);
        return saved;
    }

    @Override
    public boolean deleteEmployee(int id) {
        if(eRepo.findById(id) != null){
            this.eRepo.deleteById(id);
        return true;
        }
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDetails emp,int id) {
        if(eRepo.findById(id)!=null){
            this.eRepo.save(emp);
            return true;
        }
        return false;
      
        
    }

    @Override
    public List<EmployeeDetails> getSingleDoseEmployeeDetails() {
       List<EmployeeDetails> list=(List<EmployeeDetails>) this.eRepo.findAll();
       List<EmployeeDetails> empList=list.stream().filter(emp->emp.getNumber_of_dose()%2!=0).collect(Collectors.toList());
        return empList;
    }

    @Override
    public List<EmployeeDetails> getDoubleDoseEmployeeDetails() {
        List<EmployeeDetails> list=(List<EmployeeDetails>) this.eRepo.findAll();
       List<EmployeeDetails> empList=list.stream().filter(emp->emp.getNumber_of_dose()%2==0).collect(Collectors.toList());
      
        return empList;
    }
}
