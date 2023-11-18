package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.fastcampuspay.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.fastcampuspay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmbankingResult;
import com.fastcampuspay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.FirmbankingRequestMapper;
import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestUseCase;
import com.fastcampuspay.banking.application.port.in.UpdateFirmbankingCommand;
import com.fastcampuspay.banking.application.port.in.UpdateFirmbankingUseCase;
import com.fastcampuspay.banking.application.port.out.ExternalFirmbankingPort;
import com.fastcampuspay.banking.application.port.out.RequestFirmbankingPort;
import com.fastcampuspay.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.UseCase;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingRequestService implements RequestFirmbankingRequestUseCase, UpdateFirmbankingUseCase {

    private final RequestFirmbankingPort requestFirmbankingPort;

    private final FirmbankingRequestMapper mapper;

    private final ExternalFirmbankingPort externalFirmbankingPort;

    private final CommandGateway commandGateway;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingRequestCommand command) {

        FirmbankingRequestJpaEntity entity = requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(command.getStatus()),
                new FirmbankingRequest.AggregateIdentifier("")
        );

        FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));

        UUID uuid = UUID.randomUUID();


        if(firmbankingResult.getResultCode() == 0){
            //성공
            entity.setFirmbankingStatus(1);
        }else{
            //실패
            entity.setFirmbankingStatus(2);
        }

         requestFirmbankingPort.modify(entity);

        return mapper.mapToDomainEntity(entity, uuid);

    }

    @Override
    public void requestFirmbankingByEvent(RequestFirmbankingRequestCommand command) {

        CreateFirmbankingRequestCommand axonCommand = CreateFirmbankingRequestCommand.builder()
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .fromBankName(command.getFromBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .toBankName(command.getToBankName())
                .moneyAmount(command.getMoneyAmount())
                .build();


        commandGateway.send(axonCommand)
                .whenComplete(
                        (result, throwable) -> {

                            if(throwable != null){
                                throwable.printStackTrace();
                            }else{
                                //성공
                                //request firm db save
                                System.out.println("createFirmbankingRequestCommand completed. " + result);

                                FirmbankingRequestJpaEntity entity = requestFirmbankingPort.createFirmbankingRequest(
                                        new FirmbankingRequest.FromBankName(command.getFromBankName()),
                                        new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                                        new FirmbankingRequest.ToBankName(command.getToBankName()),
                                        new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                                        new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                                        new FirmbankingRequest.FirmbankingStatus(command.getStatus()),
                                        new FirmbankingRequest.AggregateIdentifier(result.toString())
                                );

                                FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                                        command.getFromBankName(),
                                        command.getFromBankAccountNumber(),
                                        command.getToBankName(),
                                        command.getToBankAccountNumber(),
                                        command.getMoneyAmount()
                                ));

                                if(firmbankingResult.getResultCode() == 0){
                                    entity.setFirmbankingStatus(1);
                                }else{
                                    entity.setFirmbankingStatus(2);
                                }

                                requestFirmbankingPort.modify(entity);
                            }


                        }

                );

        //command -> event sourcing

    }

    @Override
    public void updateFirmbanking(UpdateFirmbankingCommand command) {

        UpdateFirmbankingRequestCommand updateFirmbankingRequestCommand = new UpdateFirmbankingRequestCommand(
                command.getFirmbankingAggregateIdentifier(),
                command.getFirmbankingStatus()
        );

        commandGateway.send(updateFirmbankingRequestCommand)
                .whenComplete(
                        (result, throwable) -> {

                            if(throwable != null){
                                throwable.printStackTrace();
                            }else{
                                //성공
                                //request firm db save
                                System.out.println("updateFirmbankingRequestCommand Success");
                                FirmbankingRequestJpaEntity entity = requestFirmbankingPort.getFirmbankingRequest(
                                        new FirmbankingRequest.AggregateIdentifier(result.toString())
                                );
 
                                entity.setFirmbankingStatus(updateFirmbankingRequestCommand.getFirmbankingStatus());

                                requestFirmbankingPort.modify(entity);
                            }
                        }
                );

    }
}
