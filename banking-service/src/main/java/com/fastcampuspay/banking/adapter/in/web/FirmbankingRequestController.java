package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestUseCase;
import com.fastcampuspay.banking.application.port.in.UpdateFirmbankingCommand;
import com.fastcampuspay.banking.application.port.in.UpdateFirmbankingUseCase;
import com.fastcampuspay.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FirmbankingRequestController {

    private final RequestFirmbankingRequestUseCase useCase;

    private final UpdateFirmbankingUseCase updateFirmbankingUseCase;

    @PostMapping("/banking/firmbanking")
    FirmbankingRequest register(@RequestBody FirmbankingRequestRequest request){

        //UseCase
        // request -> command
        //Usecase
        RequestFirmbankingRequestCommand command = RequestFirmbankingRequestCommand.builder()
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .fromBankName(request.getFromBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .toBankName(request.getToBankName())
                .build();

        FirmbankingRequest firmbankingRequest = useCase.requestFirmbanking(command);

        return firmbankingRequest;
    }


    @PostMapping("/banking/firmbanking-eda")
    void registerFirmBankingByEvent(@RequestBody FirmbankingRequestRequest request){

        //UseCase
        // request -> command
        //Usecase
        RequestFirmbankingRequestCommand command = RequestFirmbankingRequestCommand.builder()
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .fromBankName(request.getFromBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .toBankName(request.getToBankName())
                .build();

        useCase.requestFirmbankingByEvent(command);
    }

    @PutMapping("/banking/firmbanking/update-eda")
    void updateFirmBankingByEvent(@RequestBody UpdateFirmbankingRequest request){

        //UseCase
        // request -> command
        //Usecase
        UpdateFirmbankingCommand command = UpdateFirmbankingCommand.builder()
                .firmbankingAggregateIdentifier(request.getFirmbankingRequestAggregateIdentifier())
                .firmbankingStatus(request.getStatus())
                .build();

        updateFirmbankingUseCase.updateFirmbanking(command);
    }
}
