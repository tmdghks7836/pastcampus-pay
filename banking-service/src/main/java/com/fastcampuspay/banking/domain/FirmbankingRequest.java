package com.fastcampuspay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {

    @Getter
    private final String firmbankingRequestId;

    @Getter
    private final String fromBankName;

    @Getter
    private final String fromBankAccountNumber;

    @Getter
    private final String toBankName;

    @Getter
    private final String toBankAccountNumber;

    @Getter
    private final int moneyAmount;

    @Getter
    private final int firmbankingStatus; // 0: request 1: success 2: failed

    @Getter
    private final UUID uuid;

    public static FirmbankingRequest generate(RequestedFirmbankingId requestedFirmbankingId,
                                              FromBankName fromBankName,
                                              FromBankAccountNumber fromBankAccountNumber,
                                              ToBankName toBankName,
                                              ToBankAccountNumber toBankAccountNumber,
                                              MoneyAmount moneyAmount,
                                              FirmbankingStatus firmbankingStatus,
                                              UUID uuid) {

        return new FirmbankingRequest(
                requestedFirmbankingId.value,
                fromBankName.value,
                fromBankAccountNumber.value,
                toBankName.value,
                toBankAccountNumber.value,
                moneyAmount.value,
                firmbankingStatus.value,
                uuid);
    }


    @Value
    public static class RequestedFirmbankingId {

        public RequestedFirmbankingId(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class FromBankName {

        public FromBankName(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class FromBankAccountNumber {

        public FromBankAccountNumber(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class ToBankName {

        public ToBankName(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class ToBankAccountNumber {

        public ToBankAccountNumber(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class MoneyAmount {

        public MoneyAmount(int value) {
            this.value = value;
        }

        int value;
    }

    @Value
    public static class FirmbankingStatus {

        public FirmbankingStatus(int value) {
            this.value = value;
        }

        int value;
    }

    @Value
    public static class AggregateIdentifier {

        public AggregateIdentifier(String value) {
            this.value = value;
        }

        String value;
    }
}
