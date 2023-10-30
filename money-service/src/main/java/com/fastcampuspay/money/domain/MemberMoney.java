package com.fastcampuspay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {

    @Getter
    private final String memberMoneyId;

    @Getter
    private final String membershipId;

    // 증액 요청인지 감액요청인지
    @Getter
    private final int balance;

    public static MemberMoney generate(
            MemberMoneyId memberMoneyId,
            MembershipId membershipId,
            Balance balance
    ) {

        return new MemberMoney(
                memberMoneyId.value,
                membershipId.value,
                balance.value
        );
    }


    @Value
    public static class MemberMoneyId {

        public MemberMoneyId(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class MembershipId {

        public MembershipId(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class Balance {

        public Balance(int value) {
            this.value = value;
        }

        int value;
    }
}
