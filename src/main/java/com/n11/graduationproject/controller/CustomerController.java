package com.n11.graduationproject.controller;

import com.n11.graduationproject.dto.customer.CustomerResponseDTO;
import com.n11.graduationproject.dto.customer.CustomerSaveRequestDTO;
import com.n11.graduationproject.dto.customer.CustomerUpdateRequestDTO;
import com.n11.graduationproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@Valid @RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {

        CustomerResponseDTO customerResponseDTO = customerService.save(customerSaveRequestDTO);

        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDTO> update(@Valid @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {

        CustomerResponseDTO customerResponseDTO = customerService.update(customerUpdateRequestDTO);

        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        String deleteMessage = customerService.deleteById(id);

        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }
}