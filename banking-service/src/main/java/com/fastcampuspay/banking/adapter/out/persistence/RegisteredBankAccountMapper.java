package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.domain.bankaccount.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {

    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity){

        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.RegisteredBankAccountId(
                        registeredBankAccountJpaEntity.getRegisteredBankAccountId()),
                new RegisteredBankAccount.MembershipId(registeredBankAccountJpaEntity.getMembershipId()),
                new RegisteredBankAccount.BankName(registeredBankAccountJpaEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(registeredBankAccountJpaEntity.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(registeredBankAccountJpaEntity.isLinkedStatusIsValid())
        );
    }
}
