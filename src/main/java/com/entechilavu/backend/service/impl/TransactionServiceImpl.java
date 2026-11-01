package com.entechilavu.backend.service.impl;

import com.entechilavu.backend.dto.TransactionRequest;
import com.entechilavu.backend.entity.Transaction;
import com.entechilavu.backend.repo.CategoryRespository;
import com.entechilavu.backend.repo.PaymentModeRepository;
import com.entechilavu.backend.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CategoryRespository categoryRespository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PaymentModeRepository paymentModeRepository;

    @Override
    public Transaction createTransaction(TransactionRequest request) {
        return Optional.ofNullable(request)
                .map(req -> {
                    Transaction transaction = new Transaction();
                    transaction.setAmount(req.getAmount());
                    Optional.ofNullable(req.getCategoryId())
                            .flatMap(categoryRespository::findById)
                            .ifPresent(transaction::setCategory);
                    Optional.ofNullable(req.getPaymentModeId())
                            .flatMap(paymentModeRepository::findById)
                            .ifPresent(transaction::setPaymentMode);
                    transaction.setTransactionDate(req.getTransactionDate());
                    transaction.setDescription(req.getDescription());
                    transaction.setNotes(req.getNotes());
                    transaction.setCreatedAt(OffsetDateTime.now());
                    transaction.setAmount(req.getAmount());
                    return transaction;
                })
                .map(transactionRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to save transaction"));
    }
}
