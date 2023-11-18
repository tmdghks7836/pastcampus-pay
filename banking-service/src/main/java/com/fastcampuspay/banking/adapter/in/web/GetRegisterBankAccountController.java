package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.application.port.in.GetRegisterBankAccountUseCase;
import com.fastcampuspay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.domain.bankaccount.RegisteredBankAccount;
import com.fastcampuspay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetRegisterBankAccountController {

    private final GetRegisterBankAccountUseCase registerBankAccountUseCase;

    @GetMapping("/banking/account/{membershipId}")
    RegisteredBankAccount register(@PathVariable String membershipId) {

        GetRegisteredBankAccountCommand command = GetRegisteredBankAccountCommand.builder()
                .membershipId(membershipId)
                .build();


        return registerBankAccountUseCase.getRegisterBankAccount(command);

    }


}
