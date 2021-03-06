package com.n11.graduationproject.controller;

import com.n11.graduationproject.dto.SuccessMessage;
import com.n11.graduationproject.dto.loanapplication.*;
import com.n11.graduationproject.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/loan-apps")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<SuccessMessage> save(@Valid @RequestBody LoanApplicationSaveRequestDTO loanApplicationSaveRequestDTO) {

        LoanApplicationResponseDTO loanApplicationResponseDTO
                = loanApplicationService.save(loanApplicationSaveRequestDTO);

        HttpStatus httpStatus = HttpStatus.CREATED;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Loan application has been created.");
        successMessage.addContent(loanApplicationResponseDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }

    @PostMapping(value = "/cancel")
    public ResponseEntity<SuccessMessage> cancelLoanApplication (@Valid @RequestBody LoanApplicationCancelRequestDTO loanApplicationCancelRequestDTO){

        LoanApplicationResponseDTO loanApplicationResponseDTO
                = loanApplicationService.cancelLoanApplication(loanApplicationCancelRequestDTO);

        HttpStatus httpStatus = HttpStatus.OK;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Loan application has been canceled.");
        successMessage.addContent(loanApplicationResponseDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }

    @PostMapping(value = "/query")
    public ResponseEntity<SuccessMessage> queryLoanApplication(@Valid @RequestBody LoanApplicationQueryRequestDTO loanApplicationQueryRequestDTO) {

        LoanApplicationResponseDTO loanApplicationResponseDTO
                = loanApplicationService.queryLoanApplication(loanApplicationQueryRequestDTO);

        HttpStatus httpStatus = HttpStatus.OK;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Loan application that has most recent update date has been found.");
        successMessage.addContent(loanApplicationResponseDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }
}
