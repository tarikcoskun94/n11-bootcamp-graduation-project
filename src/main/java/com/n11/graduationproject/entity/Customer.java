package com.n11.graduationproject.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity implements Serializable {

    @Column(name = "first_name",
            length = 50,
            nullable = false)
    private String firstName;

    @Column(name = "last_name",
            length = 50,
            nullable = false)
    private String lastName;

    @Column(name = "tc_identification_no",
            length = 11,
            unique = true,
            nullable = false,
            updatable = false)
    private String TCIdentificationNo;

    @Column(name = "birth_date",
            nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone_number",
            length = 15,
            unique = true,
            nullable = false)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private LoanCustomer loanCustomer;
}