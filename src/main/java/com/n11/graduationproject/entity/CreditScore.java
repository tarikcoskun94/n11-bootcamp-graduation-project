package com.n11.graduationproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "credit_scores")
public class CreditScore extends BaseEntity implements Serializable {

    @Column(name = "score",
            nullable = false)
    private Long score;

    @Column(name = "tc_identification_no",
            length = 11,
            unique = true,
            nullable = false,
            updatable = false)
    private String TCIdentificationNo;
}