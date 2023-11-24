package com.fastcampuspay.money.adapter.in.web;

import com.fastcampuspay.money.application.port.in.*;
import com.fastcampuspay.money.domain.moneychanging.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.WebAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;

    private final CreateMemberMoneyUseCase createMemberMoneyUseCase;

//    private final IncreaseMoneyRequestUseCase decreaseMoneyRequestUseCase;

    @PostMapping("/money/increase")
    MoneyChangingResultDetail increaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request) {

        // request -> command
        //Usecase
        IncreaseMoneyRequestCommand command = new IncreaseMoneyRequestCommand(
               request.getMembershipId(),

                request.getAmount());

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0, 0,
                moneyChangingRequest.getChangingMoneyAmount()
        );


        return resultDetail;
    }

   /* @PostMapping("/money/increase-async")
    MoneyChangingResultDetail increaseMoneyChangingRequestAsync(@RequestBody IncreaseMoneyChangingRequest request) {

        // request -> command
        //Usecase
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequestAsync(command);

        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0, 0,
                moneyChangingRequest.getChangingMoneyAmount()
        );


        return resultDetail;
    }*/

    @PostMapping("/money/create-member-money")
    void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {

        CreateMemberMoneyCommand command = new CreateMemberMoneyCommand(request.getMembershipId());

        createMemberMoneyUseCase.createMemberMoney(command);
    }

    @PostMapping("/money/increase-eda")
    void increaseMemberMoney(@RequestBody IncreaseMoneyChangingRequest request) {

        IncreaseMoneyRequestByMembershipIdCommand command = new IncreaseMoneyRequestByMembershipIdCommand(
                request.getMembershipId(),
                request.getAmount());

        increaseMoneyRequestUseCase.increaseMoneyRequestEvent(command);
    }

    @PostMapping("/money/decrease-eda")
    void decreaseMemberMoney(@RequestBody IncreaseMoneyChangingRequest request) {

        IncreaseMoneyRequestByMembershipIdCommand command = new IncreaseMoneyRequestByMembershipIdCommand(
                request.getMembershipId(),
                request.getAmount() * -1);

        increaseMoneyRequestUseCase.increaseMoneyRequestEvent(command);
    }
}
