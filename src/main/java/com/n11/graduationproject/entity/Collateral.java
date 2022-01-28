package com.n11.graduationproject.entity;

import com.n11.graduationproject.enum_.CollateralType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "collaterals")
public class Collateral extends BaseEntity implements Serializable {

    @Column(name = "price",
            scale = 2,
            nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",
            nullable = false)
    private CollateralType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id",
            foreignKey = @ForeignKey(name = "fk_coll_loanapp_id"),
            nullable = false,
            updatable = false)
    private LoanApplication loanApplication;
}