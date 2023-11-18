package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.fastcampuspay.banking.application.port.out.GetRegisteredBankAccountPort;
import com.fastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.fastcampuspay.banking.domain.bankaccount.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort, GetRegisteredBankAccountPort {

    private final SpringDataRegisteredBankAccountRepository registeredBankAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity create(
            RegisteredBankAccount.RegisteredBankAccountId registeredBankAccountId,
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {

        RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = new RegisteredBankAccountJpaEntity(
                registeredBankAccountId.getValue(),
                membershipId.getValue(),
                bankName.getValue(),
                bankAccountNumber.getValue(),
                linkedStatusIsValid.isValue()
        );

        return registeredBankAccountRepository.save(registeredBankAccountJpaEntity);
    }

    @Override
    public RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {

        return registeredBankAccountRepository.findFirstByMembershipId(command.getMembershipId())
                .orElseThrow(() -> new RuntimeException("findFirstByMembershipId not found "));
    }
}
