package com.fastcampuspay.remittance.application.port.out;

import com.fastcampuspay.remittance.adapter.out.service.bank.BankingInfo;

public interface BankingPort {

   BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);

   boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount);
}
