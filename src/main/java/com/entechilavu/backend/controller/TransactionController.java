package com.entechilavu.backend.controller;


import com.entechilavu.backend.dto.ResponseDto;
import com.entechilavu.backend.dto.TransactionRequest;
import com.entechilavu.backend.entity.Transaction;
import com.entechilavu.backend.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTransaction(@RequestBody TransactionRequest request) {
        transactionService.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Created"));
    }


}
