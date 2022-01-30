package com.n11.graduationproject.util;

import com.n11.graduationproject.dto.loanapplication.LoanApplicationResultDTO;
import com.n11.graduationproject.enum_.CreditLimitFactor;
import com.n11.graduationproject.enum_.LoanApplicationStatus;
import com.n11.graduationproject.enum_.LoanCustomerClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class LoanApplicationUtilTest {

    @Test
    void whenCustomerClassIsUNKNOWN_shouldBeResponseExpected() {

        LoanApplicationResultDTO expected = LoanApplicationResultDTO.builder()
                .limit(BigDecimal.ZERO)
                .status(LoanApplicationStatus.NOT_CALCULATED)
                .build();

        LoanApplicationResultDTO actual1
                = LoanApplicationUtil.checkLoanApplication(0L, BigDecimal.ONE, BigDecimal.ONE, CreditLimitFactor.FACTOR);

        Assertions.assertEquals(expected.getLimit(), actual1.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual1.getStatus());

        LoanApplicationResultDTO actual2
                = LoanApplicationUtil.checkLoanApplication(1901L, BigDecimal.ONE, BigDecimal.ONE, CreditLimitFactor.FACTOR);

        Assertions.assertEquals(expected.getLimit(), actual2.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual2.getStatus());
    }

    @Test
    void whenCustomerClassIsRED_shouldBeResponseExpected() {

        LoanApplicationResultDTO expected = LoanApplicationResultDTO.builder()
                .limit(BigDecimal.ZERO)
                .status(LoanApplicationStatus.REJECTED)
                .build();

        LoanApplicationResultDTO actual
                = LoanApplicationUtil.checkLoanApplication(250L, BigDecimal.ONE, BigDecimal.ONE, CreditLimitFactor.FACTOR);

        Assertions.assertEquals(expected.getLimit(), actual.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void whenCustomerClassIsGREEN_shouldBeResponseExpected() {

        LoanApplicationResultDTO expected = LoanApplicationResultDTO.builder()
                .limit(new BigDecimal("330000.00"))
                .status(LoanApplicationStatus.APPROVED)
                .build();

        LoanApplicationResultDTO actual
                = LoanApplicationUtil.checkLoanApplication(1500L, new BigDecimal("20000"), new BigDecimal("500000"), CreditLimitFactor.FACTOR);

        Assertions.assertEquals(expected.getLimit(), actual.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void whenCustomerClassIsYELLOWAndTotolIncomeLessThen5000_shouldBeResponseExpected() {

        LoanApplicationResultDTO expected = LoanApplicationResultDTO.builder()
                .limit(new BigDecimal("22500.00"))
                .status(LoanApplicationStatus.APPROVED)
                .build();

        LoanApplicationResultDTO actual1
                = LoanApplicationUtil.checkLoanApplication(750L, new BigDecimal("4999"), new BigDecimal("125000"), CreditLimitFactor.FACTOR);

        Assertions.assertEquals(expected.getLimit(), actual1.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual1.getStatus());

        LoanApplicationResultDTO actual2
                = LoanApplicationUtil.checkLoanApplication(750L, new BigDecimal("5000"), new BigDecimal("125000"), CreditLimitFactor.FACTOR);

        Assertions.assertNotEquals(expected.getLimit(), actual2.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual2.getStatus());
    }

    @Test
    void whenCustomerClassIsYELLOWAndTotolIncomeLessEqual10000_shouldBeResponseExpected() {

        LoanApplicationResultDTO expected = LoanApplicationResultDTO.builder()
                .limit(new BigDecimal("71136.60"))
                .status(LoanApplicationStatus.APPROVED)
                .build();

        LoanApplicationResultDTO actual1
                = LoanApplicationUtil.checkLoanApplication(750L, new BigDecimal("10000"), new BigDecimal("255683"), CreditLimitFactor.FACTOR);

        Assertions.assertEquals(expected.getLimit(), actual1.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual1.getStatus());

        LoanApplicationResultDTO actual2
                = LoanApplicationUtil.checkLoanApplication(750L, new BigDecimal("10001"), new BigDecimal("255683"), CreditLimitFactor.FACTOR);

        Assertions.assertNotEquals(expected.getLimit(), actual2.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual2.getStatus());
    }

    @Test
    void whenCustomerClassIsYELLOWAndTotolIncomeGreater10000_shouldBeResponseExpected() {

        LoanApplicationResultDTO expected = LoanApplicationResultDTO.builder()
                .limit(new BigDecimal("83922.75"))
                .status(LoanApplicationStatus.APPROVED)
                .build();

        LoanApplicationResultDTO actual1
                = LoanApplicationUtil.checkLoanApplication(750L, new BigDecimal("10001"), new BigDecimal("255683"), CreditLimitFactor.FACTOR);

        Assertions.assertEquals(expected.getLimit(), actual1.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual1.getStatus());
    }

    /**
     * LoanCustomerClass range testing
     */
    @Test
    void whenGivenValueLessThen1orGreaterThen1900_shouldBeReturnUNKNOWN() {

        LoanCustomerClass expected = LoanCustomerClass.UNKNOWN;


        LoanCustomerClass actual1 = LoanApplicationUtil.getLoanCustomerClass(0L);
        LoanCustomerClass actual2 = LoanApplicationUtil.getLoanCustomerClass(1901L);

        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);

        LoanCustomerClass actual3 = LoanApplicationUtil.getLoanCustomerClass(1L);
        LoanCustomerClass actual4 = LoanApplicationUtil.getLoanCustomerClass(1900L);

        Assertions.assertNotEquals(expected, actual3);
        Assertions.assertNotEquals(expected, actual4);
    }

    @Test
    void whenGivenValueGreaterEqual1andLessThen500_shouldBeReturnRED() {

        LoanCustomerClass expected = LoanCustomerClass.RED;


        LoanCustomerClass actual1 = LoanApplicationUtil.getLoanCustomerClass(1L);
        LoanCustomerClass actual2 = LoanApplicationUtil.getLoanCustomerClass(499L);

        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);

        LoanCustomerClass actual3 = LoanApplicationUtil.getLoanCustomerClass(0L);
        LoanCustomerClass actual4 = LoanApplicationUtil.getLoanCustomerClass(500L);

        Assertions.assertNotEquals(expected, actual3);
        Assertions.assertNotEquals(expected, actual4);
    }

    @Test
    void whenGivenValueGreaterEqual500andLessThen1000_shouldBeReturnYELLOW() {

        LoanCustomerClass expected = LoanCustomerClass.YELLOW;


        LoanCustomerClass actual1 = LoanApplicationUtil.getLoanCustomerClass(500L);
        LoanCustomerClass actual2 = LoanApplicationUtil.getLoanCustomerClass(999L);

        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);

        LoanCustomerClass actual3 = LoanApplicationUtil.getLoanCustomerClass(499L);
        LoanCustomerClass actual4 = LoanApplicationUtil.getLoanCustomerClass(1000L);

        Assertions.assertNotEquals(expected, actual3);
        Assertions.assertNotEquals(expected, actual4);

    }

    @Test
    void whenGivenValueGreaterEqual1000andLessEqual1900_shouldBeReturnGREEN() {

        LoanCustomerClass expected = LoanCustomerClass.GREEN;


        LoanCustomerClass actual1 = LoanApplicationUtil.getLoanCustomerClass(1000L);
        LoanCustomerClass actual2 = LoanApplicationUtil.getLoanCustomerClass(1900L);

        Assertions.assertEquals(expected, actual1);
        Assertions.assertEquals(expected, actual2);

        LoanCustomerClass actual3 = LoanApplicationUtil.getLoanCustomerClass(999L);
        LoanCustomerClass actual4 = LoanApplicationUtil.getLoanCustomerClass(1901L);

        Assertions.assertNotEquals(expected, actual3);
        Assertions.assertNotEquals(expected, actual4);

    }
}