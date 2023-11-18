package com.fastcampuspay.banking.domain.bankaccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRegisteredBankAccountEvent {


    private String membershipId;

    private String bankName;

    private String bankAccountNumber;
}
