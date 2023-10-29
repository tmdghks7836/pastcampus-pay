package com.fastcampuspay.banking.application.port.in;


//밖에서 안으로 들어오는 정의

import com.fastcampuspay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {

    RegisteredBankAccount register(RegisterBankAccountCommand command);
}
