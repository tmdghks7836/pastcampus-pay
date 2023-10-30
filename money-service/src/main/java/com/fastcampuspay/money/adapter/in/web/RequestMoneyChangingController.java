package com.fastcampuspay.money.adapter.in.web;

import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;

//    private final IncreaseMoneyRequestUseCase decreaseMoneyRequestUseCase;

    @PostMapping("/money/increase")
    MoneyChangingResultDetail increaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request){

        // request -> command
        //Usecase
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0,0,
                moneyChangingRequest.getChangingMoneyAmount()
        );


        return resultDetail;
    }

    @PostMapping("/money/decrease")
    MoneyChangingResultDetail decreaseMoneyChangingRequest(@RequestBody DecreaseMoneyChangingRequest request){

        // request -> command
        //Usecase
      /*  RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getTargetMembershipId())
                .bankAccountNumber(request.getBankAccountNumber())
                .moneyChangingType(request.getBankName())
                .linkedStatusIsValid(request.isLinkedStatusIsValid())
                .build();*/

       /* MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

        if(moneyChangingRequest == null){
            return null;
        }

        return moneyChangingRequest;*/
        return null;
    }

}