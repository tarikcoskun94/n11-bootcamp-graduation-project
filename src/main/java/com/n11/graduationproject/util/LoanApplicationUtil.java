package com.n11.graduationproject.util;

import com.n11.graduationproject.dto.loanapplication.LoanApplicationResultDTO;
import com.n11.graduationproject.enum_.CreditLimitFactor;
import com.n11.graduationproject.enum_.LoanApplicationStatus;
import com.n11.graduationproject.enum_.LoanCustomerClass;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class LoanApplicationUtil {

    /**
     * START: Constants
     */
    private static BigDecimal MNY5000;
    private static BigDecimal MNY10000;
    private static BigDecimal MNY20000;

    private static BigDecimal PRC10;
    private static BigDecimal PRC20;
    private static BigDecimal PRC25;
    private static BigDecimal PRC50;

    private static BigDecimal faktor;

    private static void initializeConstants(CreditLimitFactor creditLimitFactor) {
        MNY5000 = new BigDecimal("5000");
        MNY10000 = new BigDecimal("10000");
        MNY20000 = new BigDecimal("20000");

        PRC10 = new BigDecimal("0.10");
        PRC20 = new BigDecimal("0.20");
        PRC25 = new BigDecimal("0.25");
        PRC50 = new BigDecimal("0.50");

        faktor = new BigDecimal(creditLimitFactor.getValue());
    }

    /**
     * END: Constants
     */

    public static LoanApplicationResultDTO checkLoanApplication(Long creditScore, BigDecimal totalIncome,
                                                                BigDecimal totalCollateralPrice,
                                                                CreditLimitFactor creditLimitFactor) {

        LoanApplicationResultDTO loanApplicationResultDTO;

        initializeConstants(creditLimitFactor);

        LoanCustomerClass loanCustomerClass = getLoanCustomerClass(creditScore);

        switch (loanCustomerClass) {
            case RED:
                loanApplicationResultDTO = LoanApplicationResultDTO.builder()
                        .status(LoanApplicationStatus.REJECTED)
                        .limit(new BigDecimal(BigInteger.ZERO))
                        .build();
                break;

            case YELLOW:
                if (totalIncome.compareTo(MNY5000) < 0) { /** totalIncome < 5000 */

                    loanApplicationResultDTO = LoanApplicationResultDTO.builder()
                            .status(LoanApplicationStatus.APPROVED)
                            .limit(
                                    MNY10000.add(totalCollateralPrice.multiply(PRC10))
                            )
                            .build();
                } else if (totalIncome.compareTo(MNY10000) <= 0) { /** totalIncome <= 10000 */

                    loanApplicationResultDTO = LoanApplicationResultDTO.builder()
                            .status(LoanApplicationStatus.APPROVED)
                            .limit(
                                    MNY20000.add(totalCollateralPrice.multiply(PRC20))
                            )
                            .build();
                } else { /** totalIncome > 10000 */

                    loanApplicationResultDTO = LoanApplicationResultDTO.builder()
                            .status(LoanApplicationStatus.APPROVED)
                            .limit(
                                    totalIncome.multiply(faktor).multiply(PRC50).add(totalCollateralPrice.multiply(PRC25))
                            )
                            .build();
                }
                break;

            case GREEN:
                loanApplicationResultDTO = LoanApplicationResultDTO.builder()
                        .status(LoanApplicationStatus.APPROVED)
                        .limit(
                                totalIncome.multiply(faktor).add(totalCollateralPrice.multiply(PRC50))
                        )
                        .build();
                break;

            default:
                loanApplicationResultDTO = LoanApplicationResultDTO.builder()
                        .status(LoanApplicationStatus.NOT_CALCULATED)
                        .limit(new BigDecimal(BigInteger.ZERO))
                        .build();
        }

        return loanApplicationResultDTO;
    }

    public static LoanCustomerClass getLoanCustomerClass(Long creditScore) {

        LoanCustomerClass customerClass = LoanCustomerClass.UNKNOWN;
        if (creditScore >= 1 && creditScore < 500) customerClass = LoanCustomerClass.RED;
        if (creditScore >= 500 && creditScore < 1000) customerClass = LoanCustomerClass.YELLOW;
        if (creditScore >= 1000 && creditScore <= 1900) customerClass = LoanCustomerClass.GREEN;

        return customerClass;
    }
}
