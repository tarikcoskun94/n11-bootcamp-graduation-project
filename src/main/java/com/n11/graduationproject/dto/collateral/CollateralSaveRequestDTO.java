package com.n11.graduationproject.dto.collateral;

import com.n11.graduationproject.enum_.CollateralType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CollateralSaveRequestDTO {

    @Digits(integer = 10, fraction = 2)
    @NotNull(message = "Collateral price cannot be null.")
    @DecimalMin(value = "1.00")
    private BigDecimal price;

    //TODO: Enum validasyonuna bakılabilir?
    private CollateralType type;
}
