package com.fastcampuspay.money.adapter.out.service.banking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
