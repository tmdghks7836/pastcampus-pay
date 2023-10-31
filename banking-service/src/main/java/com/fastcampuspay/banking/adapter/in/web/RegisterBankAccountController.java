package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping("/banking/account")
    RegisteredBankAccount register(@RequestBody RegisterBankAccountRequest request){

        //UseCase
        // request -> command
        //Usecase
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankAccountNumber(request.getBankAccountNumber())
                .bankName(request.getBankName())
                .linkedStatusIsValid(request.isLinkedStatusIsValid())
                .build();

        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.register(command);

        if(registeredBankAccount == null){
            return null;
        }

        return registeredBankAccount;
    }

}
