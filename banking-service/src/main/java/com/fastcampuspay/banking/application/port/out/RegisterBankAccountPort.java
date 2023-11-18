package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.fastcampuspay.banking.domain.bankaccount.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccountJpaEntity create(
            RegisteredBankAccount.RegisteredBankAccountId registeredBankAccountId,
            RegisteredBankAccount.MembershipId membershipId,
                                          RegisteredBankAccount.BankName bankName,
                                          RegisteredBankAccount.BankAccountNumber bankAccountNumber,
                                          RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid);
}
