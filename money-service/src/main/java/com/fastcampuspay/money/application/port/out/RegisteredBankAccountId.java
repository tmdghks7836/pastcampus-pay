package com.fastcampuspay.money.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredBankAccountId {

    private String registeredBankAccountId;

    private String membershipId;

    private String bankName;

    private String bankAccountNumber;
}
