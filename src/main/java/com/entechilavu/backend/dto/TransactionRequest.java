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

    private Long categoryId;

    private BigDecimal amount;

    private Long paymentModeId;

    private String transactionType;

    private String notes;
}