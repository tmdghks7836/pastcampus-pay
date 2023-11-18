package com.fastcampuspay.money.application.port.out;

public interface GetRegisteredBankAccountPort {

    RegisteredBankAccountId getRegisteredBankAccount(String membershipId);
}
