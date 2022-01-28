package com.n11.graduationproject.repository;

import com.n11.graduationproject.entity.LoanApplication;
import com.n11.graduationproject.enum_.LoanApplicationStatus;
import com.n11.graduationproject.repository.customized.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanApplicationRepository extends CustomizedRepository<LoanApplication, Long> {

    Optional<LoanApplication> findByLoanCustomerIdAndStatus(Long customerId, LoanApplicationStatus status);

    @Query(" SELECT la FROM LoanApplication la " +
            " WHERE la.loanCustomer.id = :customerId " +
            " AND la.updateTime = ( " +
            " SELECT MAX (la.updateTime) FROM LoanApplication la WHERE la.loanCustomer.id = :customerId" +
            " ) ")
    Optional<LoanApplication> findByMostRecentUpdateDate(@Param("customerId") Long customerId);
}
