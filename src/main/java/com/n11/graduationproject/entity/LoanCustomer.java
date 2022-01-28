package com.n11.graduationproject.entity;

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
@Table(name = "loan_customers")
public class LoanCustomer implements Serializable {

    @Id
    @Column(name = "id",
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
            scale = 2,
            nullable = false)
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

    @PostLoad
    private void runOnLoad() {

        this.calculateTotalIncome();
    }

    @PrePersist
    private void runOnPersist() {

        this.calculateTotalIncome();
    }

    @PreUpdate
    private void runOnUpdate() {

        this.calculateTotalIncome();
    }

    private void calculateTotalIncome() {

        this.totalIncome = this.salary.add(this.additionalIncome);
    }
}