package com.fastcampuspay.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetBankAccountRequest {

    private String bankName;

    private String bankAccountNumber;
}
