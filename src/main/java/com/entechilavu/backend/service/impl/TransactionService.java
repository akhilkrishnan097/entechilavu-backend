package com.entechilavu.backend.service.impl;

import com.entechilavu.backend.dto.TransactionRequest;
import com.entechilavu.backend.entity.Transaction;

public interface TransactionService {
    Transaction createTransaction(TransactionRequest request);
}
