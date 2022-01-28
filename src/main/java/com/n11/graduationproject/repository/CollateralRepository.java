package com.n11.graduationproject.repository;

import com.n11.graduationproject.entity.Collateral;
import com.n11.graduationproject.repository.customized.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollateralRepository extends CustomizedRepository<Collateral, Long> {
}
