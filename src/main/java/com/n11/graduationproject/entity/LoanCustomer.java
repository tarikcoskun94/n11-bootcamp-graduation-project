package com.n11.graduationproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "loan_customers")
public class LoanCustomer implements Serializable {

    @Id
    @Column(name = "customer_id",
            unique = true,
            nullable = false,
            insertable = false,
            updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
            foreignKey = @ForeignKey(name = "fk_loancust_customer_id"),
            unique = true,
            nullable = false,
            updatable = false)
    @MapsId
    private Customer customer;

    @Column(name = "salary",
            scale = 2,
            nullable = false)
    private BigDecimal salary;

    @Column(name = "additional_income",
            scale = 2)
    private BigDecimal additionalIncome;

    @Column(name = "social_security_no",
            length = 13,
            unique = true,
            nullable = false)
    private String socialSecurityNo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanCustomer", cascade = CascadeType.ALL)
    private List<LoanApplication> loanApplicationList;


    /**
     * Start of possible other contents
     * Transient fields, PostLoad, PrePersist, etc
     */

    @Transient
    private BigDecimal totalIncome;
}