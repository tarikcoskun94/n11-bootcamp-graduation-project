package com.n11.graduationproject.controller;

import com.n11.graduationproject.dto.SuccessMessage;
import com.n11.graduationproject.dto.customer.CustomerResponseDTO;
import com.n11.graduationproject.dto.customer.CustomerSaveRequestDTO;
import com.n11.graduationproject.dto.customer.CustomerUpdateRequestDTO;
import com.n11.graduationproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<SuccessMessage> save(@Valid @RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {

        CustomerResponseDTO customerResponseDTO = customerService.save(customerSaveRequestDTO);

        HttpStatus httpStatus = HttpStatus.CREATED;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Customer has been created.");
        successMessage.addContent(customerResponseDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }

    @PutMapping
    public ResponseEntity<SuccessMessage> update(@Valid @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {

        CustomerResponseDTO customerResponseDTO = customerService.update(customerUpdateRequestDTO);

        HttpStatus httpStatus = HttpStatus.OK;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Customer has been updated.");
        successMessage.addContent(customerResponseDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }

    @GetMapping
    public ResponseEntity<SuccessMessage> findAll() {

        List<CustomerResponseDTO> customerResponseDTOList = customerService.findAll();

        HttpStatus httpStatus = HttpStatus.OK;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Customer has been found.");
        for (CustomerResponseDTO customerResponseDTO : customerResponseDTOList) {
            successMessage.addContent(customerResponseDTO);
        }

        return new ResponseEntity<>(successMessage, httpStatus);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<SuccessMessage> findById(@PathVariable Long id) {

        CustomerResponseDTO customerResponseDTO = customerService.findById(id);

        HttpStatus httpStatus = HttpStatus.OK;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Customer has been found.");
        successMessage.addContent(customerResponseDTO);

        return new ResponseEntity<>(successMessage, httpStatus);

    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<SuccessMessage> deleteById(@PathVariable Long id) {

        CustomerResponseDTO customerResponseDTO = customerService.deleteById(id);

        HttpStatus httpStatus = HttpStatus.OK;

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setHttpStatus(httpStatus);
        successMessage.setTimeStamp(LocalDateTime.now());
        successMessage.addMessage("Customer has been deleted");
        successMessage.addContent(customerResponseDTO);

        return new ResponseEntity<>(successMessage, httpStatus);
    }
}