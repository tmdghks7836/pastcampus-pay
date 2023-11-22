package com.fastcampuspay.payment.application.port.out;

public interface GetRegisteredBankAccountPort {

    RegisteredBankAccountId getRegisteredBankAccount(String membershipId);
}

