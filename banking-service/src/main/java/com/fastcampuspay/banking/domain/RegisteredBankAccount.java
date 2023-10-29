package com.fastcampuspay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {

    @Getter
    private final String registeredBankAccountId;

    @Getter
    private final String membershipId;

    @Getter
    private final String bankName;

    @Getter
    private final String bankAccountNumber;

    @Getter
    private final boolean linkedStatusIsValid;

    public static RegisteredBankAccount generate(RegisteredBankAccountId registeredBankAccountId,
                                                 MembershipId membershipId,
                                                 BankName bankName,
                                                 BankAccountNumber bankAccountNumber,
                                                 LinkedStatusIsValid linkedStatusIsValid) {

        return new RegisteredBankAccount(
                registeredBankAccountId.value,
                membershipId.value,
                bankName.value,
                bankAccountNumber.value,
                linkedStatusIsValid.value);
    }


    @Value
    public static class RegisteredBankAccountId {

        public RegisteredBankAccountId(String value) {
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
    public static class BankName {

        public BankName(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class BankAccountNumber {

        public BankAccountNumber(String value) {
            this.value = value;
        }

        String value;
    }

    @Value
    public static class LinkedStatusIsValid {

        public LinkedStatusIsValid(boolean value) {
            this.value = value;
        }

        boolean value;
    }
}
