package com.n11.graduationproject.dto.collateral;

import com.n11.graduationproject.enum_.CollateralType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CollateralResponseDTO {

    private Long id;
    private BigDecimal price;
    private CollateralType type;
}
