package com.n11.graduationproject.service;

import com.n11.graduationproject.api.FakeCreditScoreAPI;
import com.n11.graduationproject.api.FakeGSMAPI;
import com.n11.graduationproject.dto.collateral.CollateralResponseDTO;
import com.n11.graduationproject.dto.collateral.CollateralSaveRequestDTO;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationCancelRequestDTO;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationResponseDTO;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationSaveRequestDTO;
import com.n11.graduationproject.entity.Collateral;
import com.n11.graduationproject.entity.Customer;
import com.n11.graduationproject.entity.LoanApplication;
import com.n11.graduationproject.entity.LoanCustomer;
import com.n11.graduationproject.enum_.CollateralType;
import com.n11.graduationproject.enum_.LoanApplicationStatus;
import com.n11.graduationproject.exception.loanapplication.LoanApplicationAlreadyExistingException;
import com.n11.graduationproject.repository.LoanApplicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceTest {

    @InjectMocks
    private LoanApplicationService loanApplicationService;

    @Mock
    private LoanApplicationRepository loanApplicationRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private CollateralService collateralService;
    @Mock
    private FakeCreditScoreAPI fakeCreditScoreAPI;
    @Mock
    private FakeGSMAPI fakeGSMAPI;

    @Test
    void whenApprovedLoanFound_shouldThrowAlreadyExistingException() {

        /******************************************************| PRE-DEFINITIONS |******************************************************/
        /** PROVIDED REQUEST: LoanApplicationSaveRequestDTO */
        LoanApplicationSaveRequestDTO provided = new LoanApplicationSaveRequestDTO();
        provided.setCustomerId(1L);
        provided.setCustomerTCIdentificationNo("12345678900");

        /******************************************************| PROCESS |******************************************************/
        Mockito.when(customerService.getLoanCustomerByIdAndTCKN(provided.getCustomerId(), provided.getCustomerTCIdentificationNo()))
                .thenReturn(new LoanCustomer());

        Mockito.when(loanApplicationRepository.findByLoanCustomerIdAndStatus(provided.getCustomerId(), LoanApplicationStatus.APPROVED))
                .thenReturn(Optional.of(new LoanApplication()));

        /******************************************************| RESULT |******************************************************/
        Assertions.assertThrows(LoanApplicationAlreadyExistingException.class, () -> loanApplicationService.save(provided));
    }

    @Test
    void whenSaveRequestProvided_shouldResponseExpected() {

        /******************************************************| PRE-DEFINITIONS |******************************************************/
        /** PROVIDED REQUEST: LoanApplicationSaveRequestDTO */
        CollateralSaveRequestDTO providedCollateral1 = new CollateralSaveRequestDTO();
        providedCollateral1.setPrice(new BigDecimal("100000"));
        providedCollateral1.setType(CollateralType.CAR);

        CollateralSaveRequestDTO providedCollateral2 = new CollateralSaveRequestDTO();
        providedCollateral2.setPrice(new BigDecimal("500000"));
        providedCollateral2.setType(CollateralType.HOUSE);

        LoanApplicationSaveRequestDTO provided = new LoanApplicationSaveRequestDTO();
        provided.setCustomerId(94L);
        provided.setCustomerTCIdentificationNo("12345678900");
        provided.setCollateralList(
                Arrays.asList(providedCollateral1, providedCollateral2)
        );

        /** ENTITY: LoanApplicaion */
        Customer customer = Customer.builder()
                .phoneNumber("5463289654")
                .build();

        LoanCustomer loanCustomer = LoanCustomer.builder()
                .id(provided.getCustomerId())
                .totalIncome(new BigDecimal("25000"))
                .customer(customer)
                .build();

        LoanApplication loanApplication = LoanApplication.builder()
                .limit(new BigDecimal("350000"))
                .status(LoanApplicationStatus.APPROVED)
                .creditScore(650L)
                .totalIncome(loanCustomer.getTotalIncome())
                .loanCustomer(loanCustomer)
                .totalCollateralPrice(new BigDecimal("600000"))
                .build();
        loanApplication.setId(35L);
        loanApplication.setCreationTime(LocalDateTime.now());
        loanApplication.setUpdateTime(LocalDateTime.now());

        /** ENTITY: Collateral */
        Collateral collateral1 = Collateral.builder()
                .price(providedCollateral1.getPrice())
                .type(providedCollateral1.getType())
                .build();
        collateral1.setId(1L);
        collateral1.setCreationTime(LocalDateTime.now());
        collateral1.setUpdateTime(LocalDateTime.now());

        Collateral collateral2 = Collateral.builder()
                .price(providedCollateral2.getPrice())
                .type(providedCollateral2.getType())
                .build();
        collateral2.setId(2L);
        collateral2.setCreationTime(LocalDateTime.now());
        collateral2.setUpdateTime(LocalDateTime.now());

        List<Collateral> collateralList = Arrays.asList(collateral1, collateral2);

        /** EXPECTED RESPONSE: LoanApplicationResponseDTO */
        CollateralResponseDTO expectedCollateral1 = CollateralResponseDTO.builder()
                .id(collateral1.getId())
                .price(collateral1.getPrice())
                .type(collateral1.getType())
                .build();

        CollateralResponseDTO expectedCollateral2 = CollateralResponseDTO.builder()
                .id(collateral2.getId())
                .price(collateral2.getPrice())
                .type(collateral2.getType())
                .build();

        LoanApplicationResponseDTO expected = LoanApplicationResponseDTO.builder()
                .id(loanApplication.getId())
                .creationTime(loanApplication.getCreationTime())
                .updateTime(loanApplication.getUpdateTime())
                .customerId(loanApplication.getLoanCustomer().getId())
                .limit(loanApplication.getLimit())
                .status(loanApplication.getStatus())
                .creditScore(loanApplication.getCreditScore())
                .totalIncome(loanApplication.getTotalIncome())
                .collateralList(
                        Arrays.asList(expectedCollateral1, expectedCollateral2)
                )
                .totalCollateralPrice(
                        expectedCollateral1.getPrice().add(expectedCollateral2.getPrice())
                )
                .build();

        /******************************************************| PROCESS |******************************************************/
        Mockito.when(customerService.getLoanCustomerByIdAndTCKN(provided.getCustomerId(), provided.getCustomerTCIdentificationNo()))
                .thenReturn(loanCustomer);

        Mockito.when(loanApplicationRepository.findByLoanCustomerIdAndStatus(provided.getCustomerId(), LoanApplicationStatus.APPROVED))
                .thenReturn(Optional.empty());

        Mockito.when(fakeCreditScoreAPI.getCreditScoreByTCKN(provided.getCustomerTCIdentificationNo()))
                .thenReturn(Optional.of(650L));

        Mockito.when(loanApplicationRepository.saveAndFlush(ArgumentMatchers.any(LoanApplication.class)))
                .thenReturn(loanApplication);

        Mockito.when(collateralService.saveAll(provided.getCollateralList(), loanApplication))
                .thenReturn(collateralList);

        /******************************************************| RESULT |******************************************************/
        LoanApplicationResponseDTO actual = loanApplicationService.save(provided);

        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getCreationTime(), actual.getCreationTime());
        Assertions.assertEquals(expected.getUpdateTime(), actual.getUpdateTime());
        Assertions.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assertions.assertEquals(expected.getLimit(), actual.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual.getStatus());
        Assertions.assertEquals(expected.getCreditScore(), actual.getCreditScore());
        Assertions.assertEquals(expected.getTotalIncome(), actual.getTotalIncome());
        Assertions.assertEquals(expected.getCollateralList().size(), actual.getCollateralList().size());
        Assertions.assertEquals(expected.getTotalCollateralPrice(), actual.getTotalCollateralPrice());
    }

    @Test
    void whenCancelRequestProvided_shouldResponseExpected() {

        /******************************************************| PRE-DEFINITIONS |******************************************************/
        /** PROVIDED REQUEST: LoanApplicationCancelRequestDTO */
        LoanApplicationCancelRequestDTO provided = new LoanApplicationCancelRequestDTO();
        provided.setBirthDate(LocalDate.now());
        provided.setTCIdentificationNo("12345678900");

        /** ENTITY: LoanApplication */
        LoanCustomer loanCustomer = LoanCustomer.builder()
                .id(94L)
                .totalIncome(new BigDecimal("25000"))
                .build();

        Collateral collateral1 = Collateral.builder()
                .build();
        Collateral collateral2 = Collateral.builder()
                .build();
        List<Collateral> collateralList = Arrays.asList(collateral1, collateral2);

        LoanApplication loanApplication = LoanApplication.builder()
                .limit(new BigDecimal("350000"))
                .status(LoanApplicationStatus.CANCELLED)
                .creditScore(650L)
                .totalIncome(loanCustomer.getTotalIncome())
                .loanCustomer(loanCustomer)
                .collateralList(collateralList)
                .totalCollateralPrice(new BigDecimal("600000"))
                .build();
        loanApplication.setId(35L);
        loanApplication.setCreationTime(LocalDateTime.now());
        loanApplication.setUpdateTime(LocalDateTime.now());

        /** EXPECTED RESPONSE: LoanApplicationResponseDTO */
        CollateralResponseDTO expectedCollateral1 = CollateralResponseDTO.builder()
                .build();
        CollateralResponseDTO expectedCollateral2 = CollateralResponseDTO.builder()
                .build();

        LoanApplicationResponseDTO expected = LoanApplicationResponseDTO.builder()
                .id(loanApplication.getId())
                .creationTime(loanApplication.getCreationTime())
                .updateTime(loanApplication.getUpdateTime())
                .customerId(loanApplication.getLoanCustomer().getId())
                .limit(loanApplication.getLimit())
                .status(loanApplication.getStatus())
                .creditScore(loanApplication.getCreditScore())
                .totalIncome(loanApplication.getTotalIncome())
                .collateralList(
                        Arrays.asList(expectedCollateral1, expectedCollateral2)
                )
                .totalCollateralPrice(new BigDecimal("600000"))
                .build();

        /******************************************************| PROCESS |******************************************************/
        Mockito.when(customerService.getLoanCustomerByTCKNAndBirthDate(provided.getTCIdentificationNo(), provided.getBirthDate()))
                .thenReturn(loanCustomer);

        Mockito.when(loanApplicationRepository.findByLoanCustomerIdAndStatus(loanCustomer.getId(), LoanApplicationStatus.APPROVED))
                .thenReturn(Optional.of(loanApplication));

        Mockito.when(loanApplicationRepository.save(loanApplication))
                .thenReturn(loanApplication);

        /******************************************************| RESULT |******************************************************/
        LoanApplicationResponseDTO actual = loanApplicationService.cancelLoanApplication(provided);

        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getCreationTime(), actual.getCreationTime());
        Assertions.assertEquals(expected.getUpdateTime(), actual.getUpdateTime());
        Assertions.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assertions.assertEquals(expected.getLimit(), actual.getLimit());
        Assertions.assertEquals(expected.getStatus(), actual.getStatus());
        Assertions.assertEquals(expected.getCreditScore(), actual.getCreditScore());
        Assertions.assertEquals(expected.getTotalIncome(), actual.getTotalIncome());
        Assertions.assertEquals(expected.getCollateralList().size(), actual.getCollateralList().size());
        Assertions.assertEquals(expected.getTotalCollateralPrice(), actual.getTotalCollateralPrice());

    }
}