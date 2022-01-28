package com.n11.graduationproject.entity;

import com.n11.graduationproject.enum_.LoanApplicationStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "loan_application")
public class LoanApplication extends BaseEntity implements Serializable {

    @Column(name = "limit_",
            scale = 2,
            nullable = false)
    private BigDecimal limit;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    private LoanApplicationStatus status;

    @Column(name = "credit_score",
            nullable = false)
    private Long creditScore;

    @Column(name = "total_income",
            scale = 2,
            nullable = false)
    private BigDecimal totalIncome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
            foreignKey = @ForeignKey(name = "fk_loanapp_loancust_id"),
            nullable = false,
            updatable = false)
    private LoanCustomer loanCustomer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanApplication", cascade = CascadeType.ALL)
    private List<Collateral> collateralList;


    /**
     * Start of possible other contents
     * Transient fields, PostLoad, PrePersist, etc
     */

    @Transient
    private BigDecimal totalCollateralPrice;
}