package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.common.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {

    private final SpringDataRegisteredBankAccountRepository registeredBankAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity create(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {

        RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = new RegisteredBankAccountJpaEntity(
                membershipId.getValue(),
                bankName.getValue(),
                bankAccountNumber.getValue(),
                linkedStatusIsValid.isValue()
        );

        return registeredBankAccountRepository.save(registeredBankAccountJpaEntity);
    }
}
