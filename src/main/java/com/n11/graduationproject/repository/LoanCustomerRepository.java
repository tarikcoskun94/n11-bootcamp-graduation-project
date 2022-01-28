package com.n11.graduationproject.repository;

import com.n11.graduationproject.entity.LoanCustomer;
import com.n11.graduationproject.repository.customized.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanCustomerRepository extends CustomizedRepository<LoanCustomer, Long> {

    boolean existsBySocialSecurityNoAndIdNot(String socialSecurityNo, Long id);
}