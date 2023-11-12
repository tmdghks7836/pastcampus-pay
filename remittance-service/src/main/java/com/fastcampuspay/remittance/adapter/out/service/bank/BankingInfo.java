package com.fastcampuspay.remittance.adapter.out.service.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankingInfo {

    private String bankName;

    private String bankAccountNumber;

    private boolean isValid;
}
