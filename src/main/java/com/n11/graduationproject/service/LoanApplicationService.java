package com.n11.graduationproject.service;

import com.n11.graduationproject.api.FakeCreditScoreAPI;
import com.n11.graduationproject.api.FakeGSMAPI;
import com.n11.graduationproject.converter.LoanApplicationConverter;
import com.n11.graduationproject.dto.collateral.CollateralSaveRequestDTO;
import com.n11.graduationproject.dto.loanapplication.*;
import com.n11.graduationproject.entity.Collateral;
import com.n11.graduationproject.entity.LoanApplication;
import com.n11.graduationproject.entity.LoanCustomer;
import com.n11.graduationproject.enum_.CreditLimitFactor;
import com.n11.graduationproject.enum_.LoanApplicationStatus;
import com.n11.graduationproject.exception.creditscore.CreditScoreNotFoundException;
import com.n11.graduationproject.exception.loanapplication.LoanApplicationAlreadyExistingException;
import com.n11.graduationproject.exception.loanapplication.LoanApplicationNotCalculatedException;
import com.n11.graduationproject.exception.loanapplication.LoanApplicationNotFoundException;
import com.n11.graduationproject.repository.LoanApplicationRepository;
import com.n11.graduationproject.util.CollateralUtil;
import com.n11.graduationproject.util.LoanApplicationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerService customerService;
    private final CollateralService collateralService;
    private final FakeCreditScoreAPI fakeCreditScoreAPI;
    private final FakeGSMAPI fakeGSMAPI;

    @Transactional
    public LoanApplicationResponseDTO save(LoanApplicationSaveRequestDTO loanApplicationSaveRequestDTO) {

        /** START: Collecting required info. */
        Long customerId = loanApplicationSaveRequestDTO.getCustomerId(); //Required
        String tcIdentificationNo = loanApplicationSaveRequestDTO.getCustomerTCIdentificationNo(); //Required

        LoanCustomer loanCustomer = customerService.getLoanCustomerByIdAndTCKN(customerId, tcIdentificationNo);

        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepository
                .findByLoanCustomerIdAndStatus(customerId, LoanApplicationStatus.APPROVED);
        if (optionalLoanApplication.isPresent()) {
            throw new LoanApplicationAlreadyExistingException("Customer has already approved application.");
        }

        List<CollateralSaveRequestDTO> collateralList = loanApplicationSaveRequestDTO.getCollateralList();
        BigDecimal totalCollateralPrice = CollateralUtil.calculateTotalCollateralPrice(collateralList); //Required

        Long creditScore = this.getCreditScore(tcIdentificationNo); //Required

        BigDecimal totalIncome = loanCustomer.getTotalIncome(); //Required
        /** END: Collecting required info. */

        /** START: Checking the application request. */
        LoanApplicationResultDTO loanApplicationResultDTO = LoanApplicationUtil
                .checkLoanApplication(creditScore, totalIncome, totalCollateralPrice, CreditLimitFactor.FACTOR);

        if (loanApplicationResultDTO.getStatus() == LoanApplicationStatus.NOT_CALCULATED) {
            throw new LoanApplicationNotCalculatedException("Calculations on your application could not be completed.");
        }
        /** END: Checking the application request. */

        /** START: Persisting the application request. */
        LoanApplication loanApplication = LoanApplicationConverter
                .convertToLoanApplication(customerId, creditScore, totalIncome, loanApplicationResultDTO);

        LoanApplication savedLoanApplication = loanApplicationRepository.saveAndFlush(loanApplication);
        List<Collateral> savedCollateralList = collateralService.saveAll(collateralList, savedLoanApplication);
        savedLoanApplication.setCollateralList(savedCollateralList);
        /** END: Persisting the application request. */

        String phoneNumber = loanCustomer.getCustomer().getPhoneNumber();
        String smsMessage = "Dear customer, your loan application has been saved." + "\n" +
                "Status: " + savedLoanApplication.getStatus() + "\n" +
                "Limit: " + savedLoanApplication.getLimit();
        String signature = "Tarık Bank - 0555 555 55 55";
        fakeGSMAPI.sendSMS(phoneNumber, smsMessage, signature);

        return LoanApplicationConverter.convertToLoanApplicationResponseDTO(savedLoanApplication);
    }

    @Transactional
    public LoanApplicationResponseDTO cancelLoanApplication(LoanApplicationCancelRequestDTO loanApplicationCancelRequestDTO) {

        String tcIdentificationNo = loanApplicationCancelRequestDTO.getTCIdentificationNo();
        LocalDate birthDate = loanApplicationCancelRequestDTO.getBirthDate();

        LoanCustomer loanCustomer = customerService.getLoanCustomerByTCKNAndBirthDate(tcIdentificationNo, birthDate);

        LoanApplication loanApplication = loanApplicationRepository.findByLoanCustomerIdAndStatus(loanCustomer.getId(), LoanApplicationStatus.APPROVED)
                .orElseThrow(() -> new LoanApplicationNotFoundException("Approved loan application is not found by ID and TC identification no: "
                        + tcIdentificationNo + " | " + birthDate));

        loanApplication.setStatus(LoanApplicationStatus.CANCELLED);
        LoanApplication updatedLoanApplication = loanApplicationRepository.save(loanApplication);

        return LoanApplicationConverter.convertToLoanApplicationResponseDTO(updatedLoanApplication);
    }

    @Transactional
    public LoanApplicationResponseDTO queryLoanApplication(LoanApplicationQueryRequestDTO loanApplicationQueryRequestDTO) {

        String tcIdentificationNo = loanApplicationQueryRequestDTO.getTCIdentificationNo();
        LocalDate birthDate = loanApplicationQueryRequestDTO.getBirthDate();

        LoanCustomer loanCustomer = customerService.getLoanCustomerByTCKNAndBirthDate(tcIdentificationNo, birthDate);

        LoanApplication loanApplication = loanApplicationRepository.findByMostRecentUpdateDate(loanCustomer.getId())
                .orElseThrow(() -> new LoanApplicationNotFoundException("Loan application is not found by ID and TC identification no: "
                        + tcIdentificationNo + " | " + birthDate));

        return LoanApplicationConverter.convertToLoanApplicationResponseDTO(loanApplication);
    }


    /**
     * Private ones
     */
    protected Long getCreditScore(String TCIdentificationNo) {

        return fakeCreditScoreAPI.getCreditScoreByTCKN(TCIdentificationNo)
                .orElseThrow(() -> new CreditScoreNotFoundException("Credit score is not found by TC identification no: " + TCIdentificationNo));
    }
}
