package com.fastcampuspay.banking.application.port.in;

import com.fastcampuspay.banking.domain.bankaccount.RegisteredBankAccount;

public interface GetRegisterBankAccountUseCase {


    RegisteredBankAccount getRegisterBankAccount(GetRegisteredBankAccountCommand command);
}
