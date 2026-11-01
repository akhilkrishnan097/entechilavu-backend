package com.entechilavu.backend.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    private LocalDate transactionDate;

    private String description;

    private String category;

    private BigDecimal amount;

    private String paymentMode;

    private String transactionType;

    private String notes;
}