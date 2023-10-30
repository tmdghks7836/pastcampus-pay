package com.fastcampuspay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;
import java.util.UUID;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {

    @Getter
    private final String moneyChangingRequestId;

    @Getter
    private final String targetMembershipId;

    // 증액 요청인지 감액요청인지
    @Getter
    private final int changingType;

/*
    enum MoneyChangingType {

        INCREASING,
        DECREASING
    }
*/

    // 요청 금액
    @Getter
    private final int changingMoneyAmount;

    // 머니 변액 요청에 대한 상태
    @Getter
    private final int changingMoneyStatus;

/*    enum MoneyChangingMoneyStatus {

        REQUESTED,
        SUCCEEDED,
        FAILED,
        CANCELLED
    }*/

    @Getter
    private final String uuid;

    @Getter
    private final Date createdAt;

    public static MoneyChangingRequest generate(
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            ChangingType changingType,
            ChangingMoneyAmount changingMoneyAmount,
            ChangingMoneyStatus changingMoneyStatus,
            Uuid uuid
    ) {

        return new MoneyChangingRequest(
                moneyChangingRequestId.value,
                targetMembershipId.value,
                changingType.value,
                changingMoneyAmount.value,
                changingMoneyStatus.value,
                uuid.value,
                new Date()
        );
    }


    @Value
    public static class MoneyChangingRequestId {

        public MoneyChangingRequestId(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class TargetMembershipId {

        public TargetMembershipId(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class ChangingType {

        public ChangingType(int value) {
            this.value = value;
        }

        int value;
    }

    @Value
    public static class ChangingMoneyAmount {

        public ChangingMoneyAmount(int value) {
            this.value = value;
        }

        int value;
    }

    @Value
    public static class ChangingMoneyStatus {

        public ChangingMoneyStatus(int value) {
            this.value = value;
        }

        int value;
    }

    @Value
    public static class Uuid {

        public Uuid(String value) {
            this.value = value;
        }

        String value;
    }

}
