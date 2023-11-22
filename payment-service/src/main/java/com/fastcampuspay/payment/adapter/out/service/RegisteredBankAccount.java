package com.fastcampuspay.payment.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredBankAccount {

    private String id;

    private String membershipId;

    private String bankName;

    private String bankAccountNumber;

    private boolean linkedStatusIsValid;
}
